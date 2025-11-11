import React, { useContext, useEffect } from 'react'
import  UserContext from '../context/UserContext';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from '../serviceApi/ApiService';
import CustomerLink from './CusromerLink';
const Navbar = () => {
    const {currentUser, isRequstToGetCurrentUserDone ,updateCurrentUserContext} = useContext(UserContext);
    const navigate = useNavigate();


    const logout = () => {
      logoutUser();
      setTimeout(() => {
      updateCurrentUserContext(null);
        navigate("/");
      }, 500);
    }
useEffect(() => {
   if (isRequstToGetCurrentUserDone) {
      console.log("User role:", currentUser?.role);
    }
  }, [currentUser, isRequstToGetCurrentUserDone]); // 🔄 רק כשטוען משתמש

  if (!isRequstToGetCurrentUserDone) {
    // ⏳ אם עדיין טוען, לא מציג כלום כדי למנוע הבהוב
    return null;
  }

  return (
    <div style={{margin: 40}}>
            <CustomerLink  to="/"> Home </CustomerLink>
              {(isRequstToGetCurrentUserDone && currentUser?.role?.includes('ADMIN')) && <CustomerLink to="/admin"> Admin </CustomerLink>}
              {(isRequstToGetCurrentUserDone && currentUser) && <CustomerLink to="/" onClick={logout}> Logout </CustomerLink>}
    </div>
  )
}

export default Navbar