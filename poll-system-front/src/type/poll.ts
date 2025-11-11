export interface IQuestion {
    id?: number | undefined;
    question: string;
    first_option: string;
    second_option: string;
    third_option: string;
    fourth_option: string;
}
export interface IResult {
  question: string;
  first_option: string;
  second_option: string;
  third_option: string;
  fourth_option: string;
  count_first_option: number;
  count_second_option: number;
  count_third_option: number;
  count_fourth_option: number;
  
}
export class Answer {
    question_id: number;
    answer_number: number;
    constructor(question_id: number, answer_number: number) {
        this.question_id = question_id;
        this.answer_number = answer_number;
      
    }

}
export interface IAnswerResult{
  question: string;
  answer_number: number;
  num_users?: number;
}

export class User {
    id?: number | undefined;
    first_name: string;
    last_name: string;
    email: string;
    age: number;
    joining_date: Date;
    password: string;
    role: string;
    username: string;
  
    constructor(first_name: string, last_name: string, email: string, age: number,  joining_date: Date, password: string, role: string, username: string , id?: number | undefined) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
    
        this.joining_date = joining_date;
        this.password = password;
        this.role = role;
        this.username = username;
        this.id = id;
 
    }

   
}
export interface FormErrors {
  first_name?: string;
  last_name?: string;
  email?: string;
  age?: string;
  address?: string;
}

export interface UserContextType {
  currentUser: User | null;
  updateCurrentUserContext: (user: User | null) => void;
  isRequstToGetCurrentUserDone: boolean;
}
export class Login {
  username: string;
  password: string;
  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;
  }
} 

export interface ErrorResponse {
  timestamp: string;
  status: number;
  error: string;
  message: string;
  path: string;
}
export interface IOptionResult {
  text: string;
  votes: number;
}

export interface IQuestionResult {
  id: number;
  question: string;
  option: IOptionResult[];
}
