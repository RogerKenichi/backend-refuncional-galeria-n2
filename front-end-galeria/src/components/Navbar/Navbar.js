import React from 'react'
import { useNavigate } from 'react-router-dom'

import './Navbar.css';
export default function Navbar() {

    const navigate = useNavigate();

  return (
    <nav id="navbar">
        <p onClick={() => navigate('/')}>Logo</p>
        <ul className="list">
            <li className="item">
            <a onClick={() => navigate('/Tag')}>Tags</a>
            </li>
            <li className="item">
            <a onClick={() => navigate('/Autor')}>Autores</a>
            </li>
            <li className="item">
            <a onClick={() => navigate('/Imagem')}>Upload</a>
            </li>
            <li className="item">
            <a onClick={() => navigate('/Sobre')}>Sobre</a>
            </li>
          </ul>
    </nav>
  )
}
