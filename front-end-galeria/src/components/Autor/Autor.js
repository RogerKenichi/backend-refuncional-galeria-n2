import React, { useEffect, useState } from 'react'

//import PropTypes from 'prop-types';
import api from '../../services/apiAutor';
import { useNavigate } from 'react-router-dom'

import './Autor.css'



export default function Autor() {

    const navigate = useNavigate();
    const [autores, setAutores] = useState([]);

    function excludFunction(props) {
        api.delete('/api/autor/delete?id=' + props)
            .then(() => window.location.reload(false));
    }

    const chamaApiFindAll = async () => {
        const response = await fetch("http://localhost:8080/api/autor/findall");
        let jsonObj = await response.json();
        setAutores(jsonObj);
        console.log(jsonObj);
    }

    useEffect(() => {
          chamaApiFindAll();
    }, []);

    return (
        <>
            <div className='container-btn'>
                <button className='btnCadastrar' onClick={() => navigate('/CadastrarAutor')}>Cadastrar</button>
            </div>

            {autores.map(autores => (
                <div className='autores'>
                    <div className='autor' key={autores.id}>
                        <p>Img</p>
                        <p>
                            <strong>Nome:</strong>
                            {autores.nome}
                        </p>
                        <p>
                            <strong>Descrição:</strong>
                            {autores.descricao}
                        </p>
                        {!!autores.nome && (
                            <button className='button-edit' onClick={() => navigate('/EditarAutor/' + autores.id)}>Editar</button>
                        )} {!!autores.nome && (
                            <button className='button-excluded' onClick={() => excludFunction(autores.id)}>Excluir</button>
                        )}{!!autores.nome && (
                            <button className='button-edit' onClick={() => navigate('/ListaTodasImagensAutor/' + autores.id)}>Ver artes deste autor</button>
                        )}
                    </div>
                </div>
            ))}

        </>
    );
}