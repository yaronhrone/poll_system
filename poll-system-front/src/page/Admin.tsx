import React, { useContext, useState } from 'react'
import UserContext from '../context/UserContext';
import Users from '../componets/Users';
import FormQuestion from '../componets/FormQuestion';
import { useNavigate } from 'react-router-dom';

const Admin = () => {
    const {currentUser, isRequstToGetCurrentUserDone} = useContext(UserContext);
    const [isOpenUsers, setIsOpenUsers] = useState(false);
    const [isOpenQuestions, setIsOpenQuestions] = useState(false);
    const navigate = useNavigate();

    const toggleUsers = () => {
        setIsOpenUsers(!isOpenUsers);
    }
    const toggleQuestion = () => {
        setIsOpenQuestions(!isOpenQuestions);
    }
    if (!isRequstToGetCurrentUserDone) {
    return <div>Loading...</div>;
}



if (!currentUser?.role?.includes("ADMIN")) {
    return (
        <div className='center'>
            <h2>Unauthorized Access</h2>
            <h3>You need to login as admin to access this page.</h3>
            <button className='btn s' onClick={() => navigate("/")}>Login</button>
        </div>
    );
}
  return (
    <div>
        <button className='btn s' onClick={toggleUsers}>Users</button>
      {isOpenUsers && <Users />}
      <button className='btn s' onClick={toggleQuestion}> Question</button>
      {isOpenQuestions && <FormQuestion />}

    </div>
  )
}

export default Admin