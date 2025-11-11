import React from 'react'
import { Link, useMatch, useResolvedPath } from 'react-router-dom'
import '../style/CustomerLink.css'

interface CustomLinkProps {
    to: string;
    children: React.ReactNode;
    [key: string]: any;
}
  
const CustomLink = ({to, children , ...rest}: CustomLinkProps) => {
    const {pathname} = useResolvedPath(to);
    const isActive = useMatch({path: pathname, end: true});
  return (
    <Link to={to} {...rest} className={`custom-link ${isActive ? "active" : ""}`}>
        {children}
    </Link>
  )
}

export default CustomLink