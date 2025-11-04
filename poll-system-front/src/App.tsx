
import { BrowserRouter, Route, Routes, useNavigate } from 'react-router-dom';
import './App.css';
import Poll from './page/Poll';
import {  useEffect, useState } from 'react';
import { User } from './type/poll';
import UserContext from './context/UserContext';

import Home from './page/Home';
import NotFound from './page/NotFound';
import Result from './page/Result';
import { fetchCurrentUser } from './serviceApi/ApiService';
import Admin from './page/Admin';

function App() {
  const [isRequstToGetCurrentUserDone, setIsRequstToGetCurrentUserDone] = useState(false);
  const [currentUser, setCurrentUser] = useState<User | null>(null);

  const updateCurrentUserContext = async (user: User) => {
    console.log(user + " user from context");
    setCurrentUser(user);
    setIsRequstToGetCurrentUserDone(true);
  };
  const getCurrentUserForContext = async () => {
    try {
    if(localStorage.getItem('token')){
      const {data} = await fetchCurrentUser();
    updateCurrentUserContext(data);  
    }
  } catch (error) {
    console.error(error);
    

  }
    
  }
  useEffect(() => {
    getCurrentUserForContext();
  },[]);
  return (
    
    <div className="App">
       <UserContext.Provider value={{ currentUser, updateCurrentUserContext, isRequstToGetCurrentUserDone }}>
      <BrowserRouter>
      {currentUser?.role === 'admin' && <Admin />}
        <Routes>
          <Route path='/poll' element={<Poll />} />
          <Route path='/' element={<Home/>} />
          <Route path='*' element={<NotFound />} />
          <Route path='/resulte' element={<Result />} />
        </Routes>
      </BrowserRouter>
      </UserContext.Provider>
    </div>
  );
}

export default App;
