import React, { useEffect, useState } from 'react'
import { answerQuestion, fetchQuestion } from '../serviceApi/ApiService';
import { Answer, IQuestion } from '../type/poll';
import  { useUserContext } from '../context/UserContext';
import { useNavigate } from 'react-router-dom';
import '../style/Poll.css';
const PollQuestion = () => {
 const { currentUser, updateCurrentUserContext, isRequstToGetCurrentUserDone } = useUserContext();
  const [question, setQuestion] = useState<IQuestion| null>(null);
  const [idQ, setIdQ] = useState(1);
//   const [answer, setAnswer] = useState<Answer | null>(null);
  const navigate = useNavigate();
  const [errorFromServer, setErrorFromServer] = useState('');

  const nextQuestion =async () => {
    try {
      const data = await fetchQuestion(idQ);
 
      setQuestion(
      data
      );
      setIdQ(idQ + 1);
      
    }catch (error: any ) {
      console.error(error);
if (error.status == 400 || error.status == 404) {
  setErrorFromServer(error.response.data);
  navigate('/resulte');
} if (error.code == 'ERR_NETWORK') {
  setErrorFromServer('Network Error , pleease try again later');
}
setTimeout(() => {
  setErrorFromServer('');
}, 8000);
    }
  }
  const answerQue = (answer?: number | undefined) => {
    if (answer === undefined) {
      nextQuestion();
      return;} else
    console.log("Answer is " + answer);
    answerQuestion({ question_id: idQ - 1, answer_number: answer });
    nextQuestion();
  }
  useEffect(() => {
    nextQuestion();
  }, []);
  return (
    <>
  
    {currentUser  ? 
        
        <div className='container'>
    {question === null ?
       <div><h3>
        Loading...
        </h3>
        </div> 
       : 
       <div>
      <div className="btns">

      <h1>Poll System</h1>
      {errorFromServer && <p style={{color: "red", fontWeight: "bold"}}>{errorFromServer}</p>}
      <h2>{question.question}</h2>
      <button className='btn' onClick={() => answerQue(1)}>{question.first_option}</button>
      <button className='btn' onClick={() => answerQue(2)}>{question.second_option}</button>
      <button className='btn' onClick={() =>answerQue(3)}>{question.third_option}</button>
      <button className='btn' onClick={() =>answerQue(4)}>{question.fourth_option}</button>
      </div>
      <button className='btn' style={{color: "red", fontWeight: "bold"}} onClick={() =>answerQue()}>Next Question</button>
    </div>
    
}
  </div>
: 
   <div >
          <h2>Unauthorized Access</h2>
          <h3>You need to login to access this page.</h3>
          <button className='btn' style={{width: 150 , height: 100 , fontWeight: "bold"}} onClick={() => navigate("/")}>Home</button>
        </div>
}
  </>
  )
}
export default PollQuestion