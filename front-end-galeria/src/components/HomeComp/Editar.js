import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import './Detalhes.css'
import TagItem from '../Tag/Tag';

const EditarImagem = (props) => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [imagem, setImagem] = useState([]);
    const [tags, setTags] = useState([]);
    const [autores, setAutores] = useState([]);
    const [autorId, setAutorId] = useState([]);

    const chamaApiFind = async () => {
        const response = await fetch(`http://localhost:8080/api/imagem/find?id=${id}`);
        let jsonObj = await response.json();
        setImagem([jsonObj]);
        console.log(jsonObj);
    }
    const chamaApiTags = async () => {
        const response = await fetch(`http://localhost:8080/api/imagemxtags/find?id=${id}`);
        let jsonObj = await response.json();
        setTags(jsonObj);
        console.log(jsonObj);
        console.log(tags[0]);
    }
    const chamaApiAutores = async () => {
        const response = await fetch(`http://localhost:8080/api/autor/findall`);
        let jsonObj = await response.json();
        setAutores(jsonObj);
        console.log(jsonObj);
    }

    useEffect(() => {
        chamaApiFind();
        chamaApiTags();
        chamaApiAutores();
    }, []);

    return (
        <div>
            <h1>Editar informações da imagem</h1>

            <img className='imagemEditar' src={'http://localhost:8080/api/imagem?id=' + id} loading="lazy" />

            <form method="post" action="http://localhost:8080/api/autor/update" enctype="multipart/form-data">
                <input type="hidden" name="id" value={id} />
                <labe>Autor: </labe><input type="text" name="FK_autor_id" />
                <labe>Descrição da imagem: </labe><input type="text" name="img_descricao" /> <br />
                <select name="a" onChange={e => setAutorId(e.target.value)}>
                    {
                        autores.map(autor => (
                            <option value={autor.id}>{autor.nome}</option>
                        ))
                    }
                </select>
                <input type="submit" value="Editar" />
            </form>
        </div>
    );
}

export default EditarImagem;
