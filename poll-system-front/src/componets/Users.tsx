import React, { useEffect, useState } from 'react'
import { IAnswerResult, User } from '../type/poll';
import { deleteUser, getAllUsersAdmin, numberAnswerQuestionUser } from '../serviceApi/ApiService';
import BasicCard from './Card';

const Users = () => {
const [users, setUsers] = useState<User[]>([]);
const [errorFromServer, setErrorFromServer] = useState('');
const [result, setResult] = useState<IAnswerResult>();

const Error = (err: any) => {    
    if(err.status === 400 || err.status === 500) setErrorFromServer(err.response?.data);
    else if (err.code === 'ERR_NETWORK') setErrorFromServer('Network Error, please try again later');
    setTimeout(() => setErrorFromServer(''), 8000);
}
const getAllUsers = async () => {
    try{

        const data = await getAllUsersAdmin();
        setUsers(data);
    }catch (error: any) {
        console.log(error.data);
        Error(error);
        
    }
}
useEffect(()=> {
    getAllUsers();
}, []);
const deleteUser_ = async (id: number | undefined) => {
    try{
  await deleteUser(id);
    }catch (error: any) {
        console.log(error.data);
        Error(error);
        
    }
}
const answerUser = async (id: number | undefined) => {
    try {
        const data = await numberAnswerQuestionUser(id);
        setResult(data);
        console.log(data);
        
    } catch (error: any) {
        console.log(error.data);
        Error(error);
        
    }
}
  return (
    <div>
        <h2>Users</h2>
{users.map((user) => 
(<BasicCard
    user={user}
    onDelete={() => deleteUser_(user.id)}
    onResult={() => answerUser(user.id)}
    />

))}

    </div>
  )
}

export default Users