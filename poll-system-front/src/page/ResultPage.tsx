import React, { useContext, useEffect, useState } from 'react'
import UserContext from '../context/UserContext';
import {  IQuestionResult } from '../type/poll';
import {  result } from '../serviceApi/ApiService';
import { useNavigate } from 'react-router-dom';
import '../style/Result.css'
import PollChart from '../componets/PollChart';




function Result() {
  const { currentUser, isRequstToGetCurrentUserDone } = useContext(UserContext);
  const [errorFromServer, setErrorFromServer] = useState('');
  const navigate = useNavigate();
 
    const handleError = (err: any) => {
    console.error(err);
    if (err.status === 400 || err.status === 500) setErrorFromServer(err.response?.data);
    else if (err.code === 'ERR_NETWORK') setErrorFromServer('Network Error, please try again later');
    setTimeout(() => setErrorFromServer(''), 8000);
  };
     const [questionData, setQuestionData] = useState<IQuestionResult[]>([]);
    
      useEffect(() => { 
fetchData();
      } , []);
        const fetchData = async () => {
            try {
            const data = await result();
            setQuestionData(data);
            console.log(data);
          
          
        }catch (error: any) {
          console.error(error);
          handleError(error);
        }
      };
    
   
  

  return (
    <>
    {questionData.length === 0 &&   <p >Loading... </p>}
          <h2>Result</h2>
          {errorFromServer && <p className='error'>{errorFromServer}</p>}
      {(currentUser && isRequstToGetCurrentUserDone) ? (
        <div className='result_container'>
    {questionData?.map((question: IQuestionResult) => (
      <div className='graph' key={question.id}> 
          <PollChart 
          questionData={question}
          />
          </div>
          
    ))}
    
        </div>
        )
        :
        <div >
          <h2>Unauthorized Access</h2>
          <h3>You need to login to access this page.</h3>
          <button className='btn' style={{ width: 150, height: 100, fontWeight: "bold" ,padding: "10px" }} onClick={() => navigate("/")}>Home</button>
        </div>}
    </>
  )
}

export default Result

