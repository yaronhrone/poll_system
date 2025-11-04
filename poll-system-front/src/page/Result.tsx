import React, { useEffect, useState } from 'react'
import { useUserContext } from '../context/UserContext';
import { IAnswerResult, IQuestion } from '../type/poll';
import { getAllQuestions, numberAnswerQuestion, resultUser } from '../serviceApi/ApiService';
import { useNavigate } from 'react-router-dom';
import '../style/Result.css'
import { ClassNames } from '@emotion/react';



function Result() {
  const { currentUser, isRequstToGetCurrentUserDone } = useUserContext();
  const [answerUser, setAnserUser] = useState<IAnswerResult[]>([]);
  const [errorFromServer, setErrorFromServer] = useState('');
  const navigate = useNavigate();
  const [questions, setQuestions] = useState<IQuestion[] | undefined>([]);
  const [answerUsers, setAnswerUsers] = useState<IAnswerResult[]>([]);
  const resulteUser = async () => {
    try {
      const data = await resultUser();
      setAnserUser(data);

    } catch (error: any) {
      console.error(error);
     handleError(error);
    }
  }
    const handleError = (err: any) => {
    console.error(err);
    if (err.status === 400 || err.status === 500) setErrorFromServer(err.response?.data);
    else if (err.code === 'ERR_NETWORK') setErrorFromServer('Network Error, please try again later');
    setTimeout(() => setErrorFromServer(''), 8000);
  };
  const getAllQuestion = async () => {
    try {
      const data = await getAllQuestions();
      console.log(data);

      setQuestions(data);
    const answersArray: IAnswerResult[] = [];

      for (const q of data) {
        const answerData = await numberAnswerQuestion(q.id);
        answersArray.push(...answerData);
      }

      setAnswerUsers(answersArray);

    } catch (error: any) {
      console.error(error);
      handleError(error);
  }
}
  const answersUsersResulte = async (id: number) => {
    try {
      const data = await numberAnswerQuestion(id);
      setAnswerUsers((prev) => [...prev, ...data]);
      console.log(data);
    } catch (error: any) {
     handleError(error);
    }
  }
  const nameAnswer = (answer: number , question : IQuestion) => {
      switch (answer) {
        case 1:
          return question.first_option;
        case 2:
          return question.second_option;
        case 3:
          return question.third_option;
        case 4:
          return question.fourth_option;
        default:
          return 'Not Answer';
      }
    }
  
  useEffect(() => {
    resulteUser();
    getAllQuestion();

  }, [])
  return (
    <>
    {questions === null &&   <p>Loading... </p>}
          <h2>Result</h2>
      {(currentUser && isRequstToGetCurrentUserDone) ? (
        <div className='container_result'>
          {errorFromServer && <p style={{ color: 'red' }}>{errorFromServer}</p>}

          {questions?.map((question: IQuestion) => (
            <div key={question.id}>
              <p className='question'>{question.question}</p>
              {answerUsers?.filter((answer) => answer.question === question.question).map((answer: IAnswerResult, i: number) => (
                <p className='answer_users' key={i}>Answers users: {nameAnswer(answer.answer_number, question)} 
                <br />number of users chose this answer:  {answer.num_users}</p>
              ))}


              {answerUser?.filter((answer) => answer.question === question.question).map((answer: IAnswerResult, i: number) => (


                <p className='answer_user' key={i}>Your Answer: {nameAnswer(answer.answer_number, question)}</p>

              ))}
            </div>
          ))}

        </div>)


        :
        <div >
          <h2>Unauthorized Access</h2>
          <h3>You need to login to access this page.</h3>
          <button className='btn' style={{ width: 150, height: 100, fontWeight: "bold" }} onClick={() => navigate("/")}>Home</button>
        </div>}
    </>
  )
}

export default Result

