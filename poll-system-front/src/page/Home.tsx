import React from 'react'
import Singin from '../componets/Singin'
import Login from '../componets/Login'
import '../style/Home.css'
const Home = () => {
  return (
    <div className='home_container'>
        <Singin />
        <Login />
    </div>
  )
}

export default Home