import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import './Tag.css';

const TagItem = (props) => {
    const [tags, setTags] = useState([]);
    const [imagemId, setimagemId] = useState([]);
    const navigate = useNavigate();

    const chamaApiFind = async () => {
        const response = await fetch(`http://localhost:8080/api/tag/find?id=${props.id}`);
        let jsonObj = await response.json();
        setTags([jsonObj]);
        console.log(jsonObj);
    }

    useEffect(() => {
        chamaApiFind();
    }, []);


    if (props.formato == "imagem") {
        return (
            <div className="tagItem">
                {
                    tags.map(tag => (
                        <div>
                            <img className="tag-image" onClick={() => navigate('/ListaTodasImagensTag/' + tag.id)} src={'http://localhost:8080/api/imagem?id='+ tag.cod_tag_img_preview} loading="lazy" />
                            <div className="tag-nome">
                                <p>{tag.tag}</p>
                            </div>
                        </div>
                    ))
                }
            </div>
        );
    } else {
        return (
            <button className="btn-tag" >
                {
                    tags.map(tag => (
                        <p onClick={() => navigate('/ListaTodasImagensTag/' + tag.id)}>{tag.tag}</p>
                    ))
                }
            </button>
        );
    }
};

export default TagItem;

/*

            <div className="tag-nome">
                <p>Múltiplas pessoas juntas</p>
            </div>

        <div className="tagItem">
            <img className="tag-image" src={props.path} alt={props.nomeimagem} loading="lazy" />

            <div className="tag-sombra">
                <p>Múltiplas</p>
            </div>
        </div>
*/