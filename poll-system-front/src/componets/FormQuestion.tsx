import React, { useEffect, useState } from 'react'
import { IQuestion } from '../type/poll'
import { createQuestion, deleteQuestion, getAllQuestions, getQuestionById, updateQuestion } from '../serviceApi/ApiService';
import '../style/FormQuestion.css';

const FormQuestion = () => {
    const [question, setQuestion] = useState<IQuestion>({
        id: 0,
        question: '',
        first_option: '',
        second_option: '',
        third_option: '',
        fourth_option: ''
    });
    const [massege, setMassege] = useState('');
    const [questionsFromDB, setQuestionsFromDB] = useState<IQuestion>();
    const [errorFromServerFromUpdate,   setErrorFromServerFromUpdate] = useState('');
    const [isUpdate, setIsUpdate] = useState(true);
    const [selectedQuestionId, setSelectedQuestionId] = useState(0);
    const [errorFromServerFromAdd, setErrorFromServerFromAdd] = useState('');
    const [AllQuestions, setAllQuestions] = useState<IQuestion[]>([]);

    const fetchAllQuestions = async () => {
        try {
            const data = await getAllQuestions();
            setAllQuestions(data);
        } catch (error: any) {
            console.log(error.data);
            Error(error);
        }
    }
    useEffect(() => {
        fetchAllQuestions();
    }, []);

    const handelChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setQuestion({ ...question, [name]: value });
    }
    const handelSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!question.question || !question.first_option || !question.second_option || !question.third_option || !question.fourth_option) {
            setErrorFromServerFromAdd('All fields are required');
            return;
        }
        try {
         const data =   await createQuestion(question);
         setMassege(data);
         setQuestion({
            id: 0,
        question: '',
        first_option: '',
        second_option: '',
        third_option: '',
        fourth_option: ''
         })
         setTimeout(() => {
             setMassege('');
         },5000);
         fetchAllQuestions();
        } catch (error: any) {
            if (error.status == 400 || error.status == 500) {
                setErrorFromServerFromAdd(error.response.data);
            }
            else if (error.code == 'ERR_NETWORK') {
                setErrorFromServerFromAdd('Network Error , pleease try again later');
            }
            setTimeout(() => {
                setErrorFromServerFromAdd('');
            }, 8000);
        }

    }
    const handelDeleteQuestion = async (id: number) => {
        try {
          const data =  await deleteQuestion(id);
          setMassege(data);
          setQuestionsFromDB(undefined);
          setTimeout(() => {
              setMassege('');
          },5000);
          fetchAllQuestions();
        } catch (error: any) {
            if (error.status == 400 || error.status == 500) {
                    setErrorFromServerFromUpdate(error.response.data);
            }
            else if (error.code == 'ERR_NETWORK') {
                    setErrorFromServerFromUpdate('Network Error , pleease try again later');
            }
            setTimeout(() => {
                    setErrorFromServerFromUpdate('');
            }, 8000);
        }
    }
    const choseQuestion = async () => {
            setQuestionsFromDB(AllQuestions[selectedQuestionId - 1]);
    }
    const handleToggleUpdate =  () => {
        setIsUpdate(!isUpdate);
    }
    const handleUpdateQuestion = async ( e: React.FormEvent<HTMLFormElement>) => {
        
        e.preventDefault();
        try {
            if (!question.question || !question.first_option || !question.second_option || !question.third_option || !question.fourth_option) {
                    setErrorFromServerFromUpdate('All fields are required');
                    setTimeout(() => {
                        setErrorFromServerFromUpdate('');
                    }, 8000);
                return;
            }
            question.id = questionsFromDB?.id;
         const data = await  updateQuestion(question);
           setMassege(data);
           setIsUpdate(true);
          setTimeout(() => {
              setMassege('');
              fetchAllQuestions();
              setQuestion({
                id: 0,
                question: '',
                first_option: '',
                second_option: '',
                third_option: '',
                fourth_option: ''
              });
              setQuestionsFromDB(undefined);
          },5000);
        } catch (error: any) {
            if (error.status == 400 || error.status == 500) {
                    setErrorFromServerFromUpdate(error.response.data);
            }
            else if (error.code == 'ERR_NETWORK') {
                    setErrorFromServerFromUpdate('Network Error , pleease try again later');
            }
            setTimeout(() => {
                    setErrorFromServerFromUpdate('');
            }, 8000);
        }
    }
    return (
        <div className='container_forms'>
            <h1>Question</h1>
            <div>
                <h4>Select Question</h4>
                <select className='select'
                    value={selectedQuestionId}
                    onChange={(e) => setSelectedQuestionId(Number(e.target.value))}>
                    <option >Select Question</option>
                  {  AllQuestions?.map((q) => (
                        <option key={q.id} value={q.id}>{q.id}</option>
                    ))}
                </select>
                <button className='btn s' onClick={choseQuestion}>Submit</button>
            </div>
            {questionsFromDB && <div  key={questionsFromDB.id}>
                <form className='question_DB' onSubmit={handleUpdateQuestion}>
                    <h3>Update Question</h3>
                    <div className="question_row">
                    <label htmlFor='question'>Question:</label>
                    <input type="text" id="question" name="question" placeholder={questionsFromDB.question} onChange={handelChange} disabled={isUpdate} />
                    </div>
                    <div className="question_row">
                    <label htmlFor='first_option'>First Option:</label>
                    <input type="text" id='first_option' name="first_option" placeholder={questionsFromDB.first_option} onChange={handelChange} disabled={isUpdate} />
                    </div>
                    <div className="question_row">
                    <label htmlFor='second_option'>Second Option:</label>
                    <input type="text" id='second_option' name="second_option" placeholder={questionsFromDB.second_option} onChange={handelChange} disabled={isUpdate} />
                    </div>
                    <div className="question_row">
                    <label htmlFor='third_option'>Third Option:</label>
                    <input type="text" id='third_option' name="third_option" placeholder={questionsFromDB.third_option} onChange={handelChange} disabled={isUpdate} />
                    </div>
                    <div className="question_row">
                    <label htmlFor='fourth_option'>Fourth Option:</label>
                    <input type="text" id='fourth_option' name="fourth_option" placeholder={questionsFromDB.fourth_option} onChange={handelChange} disabled={isUpdate} />
                    </div>
                    {errorFromServerFromUpdate && <div className='error'>{errorFromServerFromUpdate}</div>}
                    {!isUpdate && <button className='btn s' type="submit">Submit</button>}
                <button  type="button" className='btn s' onClick={() => handleToggleUpdate()}>Update</button>
                <button type="button" className='btn s' onClick={() => questionsFromDB.id && handelDeleteQuestion(questionsFromDB.id)}>Delete</button>
                </form>
            </div>
            }
            
            <form className='question_DB' onSubmit={handelSubmit}>
                <h3>Add Question</h3>
                  <div className="question_row">
                <label htmlFor='question'>Question:</label>
                <input type="text" id='question' name="question" value={question.question} onChange={handelChange} />
                  </div>
                  <div className="question_row">
                <label htmlFor='first_option'>First Option:</label>
                <input type="text" id='first_option' name="first_option" value={question.first_option} onChange={handelChange} />

                  </div>
                  <div className="question_row">
                <label htmlFor='second_option'>Second Option:</label>
                <input type="text" id='second_option' name="second_option" value={question.second_option} onChange={handelChange} />

                  </div>
                  <div className="question_row">
                <label htmlFor='third_option'>Third Option:</label>
                <input type="text" id='third_option' name="third_option" value={question.third_option} onChange={handelChange} />

                  </div>
                  <div className="question_row">
                <label htmlFor='fourth_option'>Fourth Option:</label>
                <input type="text" id='fourth_option' name="fourth_option" value={question.fourth_option} onChange={handelChange} />

                  </div>
                {errorFromServerFromAdd && <div className='error'>{errorFromServerFromAdd}</div>}
                <button className='btn s' type="submit">Submit</button>
            </form>
            {massege && <div className='massege'>{massege}</div>}
        </div>
    )
}

export default FormQuestion