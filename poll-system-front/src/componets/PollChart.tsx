import Reacts from 'react';
import { BarChart, Bar, XAxis, YAxis, Tooltip, Legend, CartesianGrid, ResponsiveContainer } from 'recharts';
import { IQuestionResult } from '../type/poll';


interface Props{
  questionData : IQuestionResult | null;
}
const PollChart: React.FC<Props> = ({questionData}: Props) => {
    
       if (!questionData) return <p>Loading...</p>;
     
       const chartData = questionData?.option.map((opt: { text: string; votes: number }) => ({
         name: opt.text,
         votes: opt.votes
       }));

  return (
    <div     style={{
        width: '100%',
        maxWidth: '700px',
        height: '400px',
        margin: '40px auto',

      }}>
      <h2>{questionData.question}</h2>
      <ResponsiveContainer width="100%" height="100%" minWidth={0} minHeight={0} aspect={2}>
        <BarChart data={chartData}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis allowDecimals={false} />
          <Tooltip />
          <Legend />
          <Bar dataKey="votes" fill="#2af177ff" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default PollChart;


