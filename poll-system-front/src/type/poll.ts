export interface IQuestion {
    id: number;
    question: string;
    first_option: string;
    second_option: string;
    third_option: string;
    fourth_option: string;
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
    id?: number;
    first_name: string;
    last_name: string;
    email: string;
    age: number;
    address: string;
    joining_date: Date;
    password: string;
    role: string;
    username: string;
  
    constructor(first_name: string, last_name: string, email: string, age: number, address: string, joining_date: Date, password: string, role: string, username: string) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.joining_date = joining_date;
        this.password = password;
        this.role = role;
        this.username = username;
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
  updateCurrentUserContext: (user: User) => void;
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