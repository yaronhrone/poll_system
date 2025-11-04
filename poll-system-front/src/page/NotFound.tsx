import React from 'react'
import { useNavigate } from 'react-router-dom';
import '../style/NotFound.css'
const NotFound = () => {
    const navigate = useNavigate();
    
  return (
  <div className="container">
    <div className='not_found'>
      <h1 className='title'>Not Found 401</h1>
      <p>You are not allowed to view this page</p>
      <button className='btn_n' onClick={() => navigate("/")}>Go To Home Page</button>
    </div>
  </div>
  )
}

export default NotFound