import React, { useEffect, useState } from 'react'
import { IAnswerResult, IQuestion, User } from '../type/poll';
import { deleteUser, getAllQuestions, getAllUsersAdmin, numberAnswerQuestionUser } from '../serviceApi/ApiService';
import BasicCard from './Card';


const Users = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [errorFromServer, setErrorFromServer] = useState('');
  const [result, setResult] = useState<IAnswerResult[]>([]);
  const [questions, setQuestions] = useState<IQuestion[]>([]);
  const Error = (err: any) => {
    if (err.status === 400 || err.status === 404 || err.status === 500) setErrorFromServer(err.response?.data);
    else if (err.code === 'ERR_NETWORK') setErrorFromServer('Network Error, please try again later');
    setTimeout(() => setErrorFromServer(''), 8000);
  }
  const getAllUsers = async () => {
    try {

      const data = await getAllUsersAdmin();
      setUsers(data);
    } catch (error: any) {
      console.log(error.data);
      Error(error);

    }
  }
  useEffect(() => {
    getAllUsers();
  }, []);
  const deleteUser_ = async (id: number) => {
    try {
      await deleteUser(id);
      getAllUsers();
    } catch (error: any) {
      console.log(error.data);
      Error(error);

    }
  }
  const answerUser = async (id: number) => {
    console.log(id);

    try {
      const data = await numberAnswerQuestionUser(id);
      console.log(data);
      setResult(data);
      allQuestions();

    } catch (error: any) {
      console.log(error.data);
      Error(error);
    }
  }
  const allQuestions = async () => {
    try {
      const data = await getAllQuestions();
      setQuestions(data);
    } catch (error: any) {
      console.log(error.data);
      Error(error);
    }
  }
  const nameAnswer = (answer: number, question: IQuestion) => {
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
  return (
    <div>
      <h2>Users</h2>
      {users?.map((user) =>
      (<div className="users" key={user.id}>
        <BasicCard
          user={user}
          onDelete={deleteUser_}
          onResult={answerUser}
        />
      </div>
      ))}
      {errorFromServer && <p className='error'>{errorFromServer}</p>}
      {questions?.map((question: IQuestion) => (
        <div key={question.id}>
          <p className='question'>{question.question}</p>
          {result?.filter((answer) => answer.question === question.question).map((answer: IAnswerResult, i: number) => (
            <p className='answer_user' key={i}>Your Answer: {nameAnswer(answer.answer_number, question)}</p>
          ))}
        </div>
      ))}



    </div>
  )
}

export default Users