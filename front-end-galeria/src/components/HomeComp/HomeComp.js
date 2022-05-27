import React, { useEffect, useState } from 'react'
import api from '../../services/apiAutor';
import { useNavigate } from 'react-router-dom'

import './HomeComp.css'
import TagItem from '../Tag/Tag';

export default function HomeComp() {
  const navigate = useNavigate();
  const [imagens, setImagnens] = useState([]);

  const chamaApi = async () => {
    const response = await fetch("http://localhost:8080/api/imagem/findall");
    let imgJson = await response.json();
    setImagnens(imgJson);
    console.log(imgJson);
  }

  useEffect(() => {
    chamaApi();
  }, []);

  return (
    <>
      <div id='container'>

        {imagens.map(imagens => (
          <div key={imagens.id} className='card' onClick={() => navigate('/Detalhes/' + imagens.id)}>
            <img className='imagemPequena' src={'http://localhost:8080/api/imagem?id='+imagens.id} />
          </div>
        ))}
      </div>
    </>
  )
}
