import React, { useState } from 'react'
import { useUserContext } from '../context/UserContext';
import Users from '../componets/Users';

const Admin = () => {
    const {currentUser, isRequstToGetCurrentUserDone} = useUserContext();
    const [isOpenUsers, setIsOpenUsers] = useState(false);

    const toggleUsers = () => {
        setIsOpenUsers(!isOpenUsers);
    }
  return (
    <div>
        <button onClick={toggleUsers}>Users</button>
      {isOpenUsers && <Users />}
    </div>
  )
}

export default Admin