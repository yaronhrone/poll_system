import axios from "axios";
import { Answer, IQuestion, Login,  User} from "../type/poll";


const POLL_URL = "http://localhost:8081/question";
const USER_URL = "http://localhost:8080";

const setAuthHeader = (token : string) => {
   
    localStorage.setItem("token", token);
    
}
const removeAuthHeader = () => {
    localStorage.removeItem("token");
}

const getAuthHeader = () => {
    const token = localStorage.getItem("token");

    
    return {    
            Authorization: `Bearer ${token}`
        };
      }

export const fetchQuestion = async (id: number)  => {
  const response = await axios.get(`${POLL_URL}/${id}`);
  
  return response.data;
}
export const logoutUser = () => {
  removeAuthHeader();
}

export const fetchCurrentUser = () => {
    return axios.get(`${USER_URL}/users` , {headers: getAuthHeader()});
}
export const answerQuestion = async (answer: Answer) => {

  
  await axios.post(`${USER_URL}/poll/answer`, answer,{headers: getAuthHeader()});
 
}
export const singinUser = async (user: User) => {
  console.log(user + " user from api service");
  const {data} = await axios.post(`${USER_URL}/users/register`, user );
  const token = data.jwt;
  setAuthHeader(token);
  console.log(token + "token from singin user");
  
  return token;
}
export const loginUser = async (user: Login) => {
  const {data} = await axios.post(`${USER_URL}/authenticate`, user );
    setAuthHeader(data.jwt);
  return data.jwt;
}

export const getAllQuestions = async () => {
  const {data} = await axios.get(`${POLL_URL}/all`);
  return data;
}
export const answerQuestionUser = async () =>{
 const {data}  = await axios.get(`$(USER_URL)/answer-user-by-id`, {headers: getAuthHeader()});
 return data;
}
export const resultUser = async () =>{ 
  const {data}  = await axios.get(`${USER_URL}/poll/answer-user-by-username`, {headers: getAuthHeader()});
  return data;
}
export const numberUserAnswer = async () =>{ 
  const {data}  = await axios.get(`${USER_URL}/poll/number-user-answer`, {headers: getAuthHeader()});
  return data;
}
export const numberAnswerQuestion = async (id : number) =>{ 
  const {data}  = await axios.get(`${POLL_URL}/number-answer-question/${id}`);
  return data;
}
export const getAllUsersAdmin = async() => {
  const {data} = await axios.get(`${USER_URL}/admin/all-users`, {headers: getAuthHeader()});

  
  return data;
}
export const deleteUser = async (id : number) => {
  const {data} = await axios.delete(`${USER_URL}/admin/delete-user/?id=${id}`, {headers: getAuthHeader()});
  return data;
}
export const numberAnswerQuestionUser = async (id : number) =>{ 
  const {data}  = await axios.get(`${POLL_URL}/answer-user/${id}`);
  return data;
}
export const createQuestion = async (question: IQuestion) => {
  
  const {data} = await axios.post(`${USER_URL}/admin/create-question`, question, {headers: getAuthHeader()});
  return data;
}
export const updateQuestion = async (question: IQuestion) => {
  const {data} = await axios.put(`${USER_URL}/admin/update-question`, question, {headers: getAuthHeader()});
  return data;
}
export const deleteQuestion = async (id : number) => {
  const {data} = await axios.delete(`${USER_URL}/admin/delete-question/?id=${id}`, {headers: getAuthHeader()});
  return data;
}
export const getQuestionById = async (id : number) => {
  const {data} = await axios.get(`${USER_URL}/poll/${id}`, {headers: getAuthHeader()});
  return data;
}
export const result = async () => {
  const {data} = await axios.get(`${POLL_URL}/result`);

  
  return data;
}