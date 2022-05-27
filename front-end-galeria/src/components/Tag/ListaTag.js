import React, { useEffect, useState } from 'react'
import api from '../../services/apiAutor';
import { useNavigate } from 'react-router-dom'

import TagItem from '../Tag/Tag';

export default function ListaTag() {
    const navigate = useNavigate();
    const [imagens, setImagnens] = useState([]);

    const chamaApi = async () => {
        const response = await fetch("http://localhost:8080/api/tag/findall");
        let imgJson = await response.json();
        setImagnens(imgJson);
        console.log(imgJson);
    }

    useEffect(() => {
        chamaApi();
    }, []);

    return (
        <>
            <div className='container-btn'>
                <button className='btnCadastrar' onClick={() => navigate('/CadastrarTag')}>Cadastrar Tag</button>
            </div>
            <div id='container'>

                {imagens.map(imagens => (
                    <TagItem formato="imagem" id={imagens.id} src={`http://localhost:8080/api/autor/find?id=${imagens.cod_tag_img_preview}`}></TagItem>
                ))}
            </div>
        </>
    )
}
