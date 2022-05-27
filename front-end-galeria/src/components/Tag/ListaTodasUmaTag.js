import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'

export default function ListaTodasImagensTag(props) {
  const navigate = useNavigate();
  const { id } = useParams();
  const [imagens, setImagnens] = useState([]);
  const [tag, setTag] = useState([]);

  const chamaApi = async () => {
    const response = await fetch(`http://localhost:8080/api/imagem/findalltag?id=${id}`);
    let imgJson = await response.json();
    setImagnens(imgJson);
    console.log(imgJson);
  }
  const chamaApiTag = async () => {
    const response = await fetch(`http://localhost:8080/api/tag/find?id=${id}`);
    let imgJson = await response.json();
    setTag([imgJson]);
    console.log(imgJson);
  }

  useEffect(() => {
    chamaApi();
    chamaApiTag();
  }, []);

  return (
    <>{tag.map(t => (
      <div>
      <h1>{t.tag}</h1>
      <p>Mostrando todas as imagens marcadas com {t.tag}</p>
      </div>
    ))}
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
