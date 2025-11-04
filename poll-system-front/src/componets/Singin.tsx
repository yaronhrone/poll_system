import React, { useContext, useEffect, useState } from 'react'
import { Login, User } from '../type/poll';
import { loginUser, singinUser } from '../serviceApi/ApiService';
import { useNavigate } from 'react-router-dom';
import { FormErrors } from '../type/poll';
import '../style/Singin.css';
import { useUserContext } from '../context/UserContext';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
function Singin() {
  const navigite = useNavigate();
  const { currentUser, updateCurrentUserContext, isRequstToGetCurrentUserDone } = useUserContext();
  const [formData, setFormData] = useState<User>({
    first_name: "",
    last_name: "",
    email: "",
    age: 0,
    address: "",
    joining_date: new Date(),
    password: "",
    role: 'USER',
    username: "",
  });
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    })
    valibateForm(name, value);
  }


  const [error, setError] = useState<FormErrors>({});
  const [isFormValid, setIsFormValid] = useState(false);
  const [errorFromServer, setErrorFromServer] = useState('');
  const firstAndLastNameRegex = /^[A-Za-z\u0590-\u05FF]{2,30}$/;
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const addressRegex = /^[A-Za-z0-9\u0590-\u05FF\s,.'-]{5,100}$/;
  const usernameRegex = /^[a-zA-Z][a-zA-Z0-9]{3,23}$/;
  const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

  const valibateForm = (name: string, value: string) => {
    let errorField: string = "";
    if (!value.trim() && ['first_name', 'last_name', 'address', 'email', 'username', 'password'].includes(name)) {
      errorField = `${name.replace('_', ' ')} is required`;
    } else if (name === 'email' && !emailRegex.test(value)) {
      errorField = 'Invalid email address';
    } else if (name === 'username' && !usernameRegex.test(value)) {
      errorField = 'Invalid username';
    } else if (name === 'password' && !passwordRegex.test(value)) {
      errorField = 'Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character.';
    } else if (name === 'address' && !addressRegex.test(value)) {
      errorField = 'Address must be at least 5 characters long';
    } else if (name === 'first_name' && !firstAndLastNameRegex.test(value)) {
      errorField = 'First name must be at least 2 characters long';
    } else if (name === 'last_name' && !firstAndLastNameRegex.test(value)) {
      errorField = 'Last name must be at least 2 characters long';
    }

    setError({
      ...error,
      [name]: errorField
    });
  };
  useEffect(() => {
    const { first_name, last_name, address, email, password, username } = formData;
    setIsFormValid(
      Boolean(password && username && first_name && last_name && address && email) &&
      Object.values(error).every((error: any) => !error)
    )
  }, [error, formData]);


  const singin = async (e: React.FormEvent<HTMLFormElement>) => {
    console.log(formData, " form data from singin");

    e.preventDefault();
    if (!isFormValid) return;
    try {
      const data = await singinUser(formData);
      updateCurrentUserContext(data);
      navigite('/poll');
    } catch (error: any) {
      console.error(error);
      if (error.status == 400 || error.status == 500) {
        setErrorFromServer(error.response.data);
      } if (error.code == 'ERR_NETWORK') {
        setErrorFromServer('Network Error , pleease try again later');
      }
      setTimeout(() => {
        setErrorFromServer('');
      }, 8000);

    }
  }
  return (
    <div className='singin-container'>

      <form onSubmit={singin} className='singin-form'>
        <h1>Sing In Page</h1>
        {errorFromServer && <div className='server-error' style={{ color: 'red' }}>{errorFromServer}</div>}
        {['first_name', 'last_name', 'email', 'age', 'address', 'username'].map((field) => (
          <div key={field} className="form-group">

            <input
              type={field === 'email' ? 'email' : field === 'age' ? 'number' : 'text'}
              id={field}
              name={field}
              onChange={handleChange}
              placeholder={'Enter your ' + field.replace('_', ' ')}
            />
            {error[field as keyof FormErrors] && (
              <span className="error">{error[field as keyof FormErrors]}</span>
            )}
          </div>
        ))}
            <div className="form-group" style={{ position: 'relative', marginBottom: "1rem" }}>
              <input type={showPassword ? 'text' : 'password'} id='password' name='password' onChange={handleChange} placeholder='Enter your password' />
              <span onClick={togglePasswordVisibility} style={{ position: "absolute", right: "10px", top: "50%", transform: "translateY(-50%)", cursor: "pointer" }}>
                {showPassword ? <VisibilityIcon style={{ fontSize: "20px" }} /> : <VisibilityOffIcon style={{ fontSize: "20px" }} />}</span>
            </div>
            {error['password' as keyof FormErrors] && <p className="error">{error['password' as keyof FormErrors] }</p>}

        <button type="submit" disabled={!isFormValid} className='singin-btn'>Sing In</button>
      </form>

    </div>

  )
}

export default Singin