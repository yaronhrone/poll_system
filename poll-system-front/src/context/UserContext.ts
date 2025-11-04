import React, { createContext, useContext } from "react";
import { UserContextType, User } from "../type/poll";

const defaultContext: UserContextType = {
  currentUser: null,
  updateCurrentUserContext: (user: User) => {},
  isRequstToGetCurrentUserDone: false,
};

const UserContext = createContext<UserContextType>(defaultContext);


export const useUserContext = () => {
 return useContext(UserContext)};

export default UserContext;