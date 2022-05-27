import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";

const Detalhes = (props) => {
    const navigate = useNavigate();
    const { id } = useParams()
    const [autor, setAutor] = useState([]);

    const chamaApiFind = async () => {
        const response = await fetch(`http://localhost:8080/api/autor/find?id=${id}`);
        let jsonObj = await response.json();
        setAutor(jsonObj);
        console.log(jsonObj);
    }

    useEffect(() => {
        chamaApiFind();
    }, []);

    return (
        <div>
            <p>Id = {id}</p>
            {/*autor.map(autores => (
            <div>
                <p>ID: {autores.id}</p>
                <p>Nome: {autores.nome}</p>
                <p>Descrição: {autores.descricao}</p>
                <p>CodImgPreview: {autores.cod_autor_img_preview}</p>
            </div>
            ))*/}
        </div>
    );
}


export default Detalhes;