import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'

export default function ListaTodasImagensAutor(props) {
  const navigate = useNavigate();
  const { id } = useParams();
  const [imagens, setImagnens] = useState([]);
  const [autor, setAutor] = useState([]);

  const chamaApi = async () => {
    const response = await fetch(`http://localhost:8080/api/imagem/findallautor?id=${id}`);
    let imgJson = await response.json();
    setImagnens(imgJson);
    console.log(imgJson);
  }

  const chamaApiFind = async () => {
    const response = await fetch(`http://localhost:8080/api/autor/find?id=${id}`);
    let jsonObj = await response.json();
    setAutor([jsonObj]);
    console.log(jsonObj);
}

  useEffect(() => {
    chamaApi();
    chamaApiFind();
  }, []);

  return (
    <>
      <div id='container'>
        {
            autor.map(autor=>(
                <h1>{autor.nome}</h1>
            ))
        }

        {imagens.map(imagens => (
          <div key={imagens.id} className='card' onClick={() => navigate('/Detalhes/' + imagens.id)}>
            <img className='imagemPequena' src={'http://localhost:8080/api/imagem?id='+imagens.id} />
          </div>
        ))}
      </div>
    </>
  )
}
