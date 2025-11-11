
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Poll from './page/Poll';
import { useEffect, useState } from 'react';
import { User } from './type/poll';
import UserContext from './context/UserContext';
import Home from './page/Home';
import NotFound from './page/NotFound';
import ResultPage from './page/ResultPage';
import { fetchCurrentUser } from './serviceApi/ApiService';
import Admin from './page/Admin';
import Navbar from './componets/Navbar';

function App() {
  const [isRequstToGetCurrentUserDone, setIsRequstToGetCurrentUserDone] = useState(false);
  const [currentUser, setCurrentUser] = useState<User | null>(null);
 

  const updateCurrentUserContext = async (user: User | null) => {
    console.log(user + " user from context");
    setCurrentUser(user);
    console.log(user + " user update");
  };
  const getCurrentUserForContext = async () => {
    try {
      if (localStorage.getItem('token')) {
        const { data } = await fetchCurrentUser();
        console.log(data + " user fetch");
        updateCurrentUserContext(data);
    
      }
    } catch (error) {
      console.error(error);
    }finally {
      setIsRequstToGetCurrentUserDone(true);
    }

  }
  useEffect(() => {
    if(isRequstToGetCurrentUserDone){
      console.log("User role:", currentUser?.role ?? 'no user')}
  
    }, [currentUser]);
    
    useEffect(() => {
      getCurrentUserForContext();
  }, []);
    if (!isRequstToGetCurrentUserDone) {
    return <div style={{ textAlign: "center", marginTop: "4rem" }}>Loading...</div>;
  }
  return (

    <div className="App">
      <UserContext.Provider value={{ currentUser, updateCurrentUserContext, isRequstToGetCurrentUserDone }}>
        <Router>
          <Navbar />
          <Routes>
            <Route path='/admin' element={<Admin />} />
            <Route path='/poll' element={<Poll />} />
            <Route path='/' element={<Home />} />
            <Route path='*' element={<NotFound />} />
            <Route path='/resulte' element={<ResultPage />} />
          </Routes>
        </Router>
      </UserContext.Provider>
    </div>
  );
}

export default App;
