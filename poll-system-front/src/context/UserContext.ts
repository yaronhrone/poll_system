import React, { createContext, useContext } from "react";
import { UserContextType, User } from "../type/poll";

const defaultContext: UserContextType = {
  currentUser: null,
  updateCurrentUserContext: (user: User | null) => {},
  isRequstToGetCurrentUserDone: false,
};

 const UserContext = createContext<UserContextType>(defaultContext);


export const useUserContext = () => {
  console.log(UserContext + " context");
  
 return useContext(UserContext)};

export default UserContext;