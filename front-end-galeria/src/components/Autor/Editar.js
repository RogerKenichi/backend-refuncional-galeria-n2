import React, { useEffect, useState } from 'react';
import api from '../../services/apiAutor';
import { useParams, useNavigate } from 'react-router-dom'

export default function EditarAutor() {
    const navigate = useNavigate();
    const { id } = useParams()
    const [autor, setAutor] = useState([]);

    const chamaApiFind = async () => {
        const response = await fetch(`http://localhost:8080/api/autor/find?id=${id}`);
        let jsonObj = await response.json();
        setAutor([jsonObj]);
        console.log(jsonObj);
    }

    useEffect(() => {
        chamaApiFind();
    }, []);

    return (
        <div>
            {
                autor.map(a => (
                    <div>
                        <div>Editando autor</div>
                        <form method="post" action="http://localhost:8080/api/autor/update" enctype="multipart/form-data">
                            <input type="hidden" name="id" value={id} />
                            <labe>Nome do autor: </labe><input type="text" name="nome" />
                            <br />
                            <labe>Descrição do autor: </labe> <input type="text" name="descricao" />
                            <br />
                            <input type="hidden" name="cod_img_preview" value="0" />
                            <input type="submit" value="Editar" />
                        </form>
                    </div>
                ))
            }
        </div>
    )
}
