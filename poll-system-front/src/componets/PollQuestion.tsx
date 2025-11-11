import React, { useContext, useEffect, useState } from 'react'
import { answerQuestion, fetchQuestion, getAllQuestions } from '../serviceApi/ApiService';
import { Answer, IQuestion } from '../type/poll';
import UserContext from '../context/UserContext';
import { useNavigate } from 'react-router-dom';
import '../style/Poll.css';


const PollQuestion = () => {
  const { currentUser, updateCurrentUserContext, isRequstToGetCurrentUserDone } = useContext(UserContext);
  const [question, setQuestion] = useState<IQuestion | null>(null);
  const [idQ, setIdQ] = useState(0);
  const [allQuestions, setAllQuestions] = useState<IQuestion[]>([]);
  const navigate = useNavigate();
  const [errorFromServer, setErrorFromServer] = useState('');

  const nextQuestion = (data: IQuestion[]) => {
    console.log(idQ, data.length);

    if (idQ === data.length) {

      navigate('/resulte');
    }
    setQuestion(data[idQ] || null);
    setIdQ(idQ + 1);
  }
  const fetchAllQuestions = async () => {
    try {
      const data = await getAllQuestions();
      setAllQuestions(data);
      nextQuestion(data);
    } catch (error: any) {
      console.error(error);
      if (error.status == 400 || error.status == 404) {
        setErrorFromServer(error.response.data);

      } if (error.code == 'ERR_NETWORK') {
        setErrorFromServer('Network Error , pleease try again later');
      }
      setTimeout(() => {
        setErrorFromServer('');
      }, 8000);
    }
  }
  const answerQue = async (answer: number) => {
    try {
      if (answer === -1) {
        nextQuestion(allQuestions);
        return;
      } else
        await answerQuestion({ question_id: question?.id ? question.id : 0, answer_number: answer });
      nextQuestion(allQuestions);
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

  useEffect(() => {
    fetchAllQuestions();
  }, []);
  return (
    <>
      {currentUser || !isRequstToGetCurrentUserDone ?

        <div className='container'>
          {!question ?
            <div><h3>
              Loading...
            </h3>
            </div>
            :
            <div>
              <div className="btns">

                <h1>Poll System</h1>
                <h2>{question.question}</h2>
                {errorFromServer && <p className='error'>{errorFromServer}</p>}
                <button className='btn' onClick={() => answerQue(1)}>{question.first_option}</button>
                <button className='btn' onClick={() => answerQue(2)}>{question.second_option}</button>
                <button className='btn' onClick={() => answerQue(3)}>{question.third_option}</button>
                <button className='btn' onClick={() => answerQue(4)}>{question.fourth_option}</button>
              </div>
              <button className='btn' style={{ color: "green", fontWeight: "bold" }} onClick={() => answerQue(-1)}>Next Question</button>
            </div>

          }
        </div>
        :
        <div >
          <h2>Unauthorized Access</h2>
          <h3>You need to login to access this page.</h3>
          <button className='btn' style={{ width: 150, height: 100, fontWeight: "bold" }} onClick={() => navigate("/")}>Home</button>
        </div>
      }
    </>
  )
}
export default PollQuestion