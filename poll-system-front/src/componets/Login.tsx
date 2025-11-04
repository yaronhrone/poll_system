import React, { useState } from 'react'
import { useUserContext } from '../context/UserContext';
import { loginUser } from '../serviceApi/ApiService';
import '../style/Singin.css';
import { useNavigate } from 'react-router-dom';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';

const Login = () => {
    const { updateCurrentUserContext } = useUserContext();
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState('');
    const [errorFromServer, setErrorFromServer] = useState('');
    const navigate = useNavigate();
    const [showPassword, setShowPassword] = useState(false);

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const handelLogin = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log(username + password + " from loging");
        
        if (!username || !password) {
            setErrorFromServer('Please enter username and password');
            setTimeout(() => {
                setErrorFromServer('');
            }, 8000);
            return;
        }
        try {
            const data = await loginUser({ username: username, password: password });
            updateCurrentUserContext(data);
            navigate('/poll');

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
            <form onSubmit={handelLogin} className='singin-form'>
                <h1>Login Page</h1>
                {errorFromServer && <p className='server-error'>{errorFromServer}</p>}
                <div className="form-group">
                    <input type="text" id="username" name="username" onChange={ (e) => setUsername(e.target.value)} placeholder="Enter your username" style={{ width: "100%", paddingRight: "0", paddingLeft: "0.5rem", marginBottom: "0" }} />
                </div>
                <div className="form-group" style={{ position: 'relative', marginBottom: "1rem" }}>
                    <input type={showPassword ? 'text' : 'password'} id='password' name='password' onChange={(e) => setPassword( e.target.value)} placeholder='Enter your password' />
                    <span onClick={togglePasswordVisibility} style={{ position: "absolute", right: "10px", top: "50%", transform: "translateY(-50%)", cursor: "pointer" }}>
                        {showPassword ? <VisibilityIcon style={{ fontSize: "20px" }} /> : <VisibilityOffIcon style={{ fontSize: "20px" }} />}</span>

                </div>
                <button type="submit" className='singin-btn'  >Login</button>
            </form>
        </div>
    )
}

export default Login

