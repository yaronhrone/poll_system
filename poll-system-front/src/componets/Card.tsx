import React from 'react'
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Box, Card } from '@mui/material';
import { User } from '../type/poll';

 interface Props {
    user: User | null ,
    onDelete: (id: number)  => void,
    onResult: (id: number) => void,
}

const bull = (
  <Box
    component="span"
    sx={{ display: 'inline-block', mx: '2px', transform: 'scale(0.8)' }}
  >
    •
  </Box>
);

export default function BasicCard({user , onDelete , onResult}: Props) {
    if (!user) return null;
    
  const safeOnDelete = onDelete || (() => {});
  const safeOnResult = onResult || (() => {});
  return (
    <Card sx={{ minWidth: 275 }}>
      <CardContent>
        <Typography gutterBottom sx={{ color: 'text.secondary', fontSize: 14 }}>
          {user.role}
        </Typography>
        <Typography variant="h5" component="div">
          {user.username}
        </Typography>
        <Typography sx={{ color: 'text.secondary', mb: 1.5 }}>{user.email}</Typography>
        <Typography variant="body2">
            {user.first_name} {user.last_name}
        </Typography>
      </CardContent>
      <CardActions>
        <Button  onClick={() => user.id && safeOnDelete(user.id)} size="small">Delete</Button>
        <Button  onClick={() =>  user.id && safeOnResult(user.id)} size="small">Result</Button>
      </CardActions>
    </Card>
  );
}
