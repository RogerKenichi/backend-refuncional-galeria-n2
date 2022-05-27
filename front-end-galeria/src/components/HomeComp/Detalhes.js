import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import './Detalhes.css'
import TagItem from '../Tag/Tag';

const Detalhes = (props) => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [imagem, setImagem] = useState([]);
  const [tags, setTags] = useState([]);
  const [autores, setAutores] = useState([]);

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
      const response = await fetch(`http://localhost:8080/api/autor/find?id=${imagem.autor_id}`);
      let jsonObj = await response.json();
      setAutores([jsonObj]);
      console.log(jsonObj);
  }

  useEffect(() => {
    chamaApiFind();
    chamaApiTags();
    chamaApiAutores();
  }, []);

  return (
    <div>
      {imagem.map(img => (
        <div>
          <img className='imagemGrande' src={'http://localhost:8080/api/imagem?id='+ id} alt={id} loading="lazy" />
          <p>ID da imagem: {img.id}</p>
          <p>Autor: {autores.nome}</p>
          <p>Descrição: {img.img_descricao}</p>
          <p>Data de upload: {img.data_envio}</p>
          <button className='button-edit' onClick={() => navigate('/EditarImagem/'+ img.id)}>Editar</button>
          
          <div>
            {tags.map(tag => (
              <div className='detalhesImagemTags'>
                <TagItem formato="tag" id={tag.tag_id}></TagItem>
              </div>
            ))}
          </div>
        
        
        </div>
      ))}
      {/* !!props.length > 0 && props.map(imagens => (
            <>
          <div key={props.id} onClick={() => {}}>
            <img src={props.path}/>
            <form onSubmit={this.handleSubmit}>
                <label>
                    Caminho da imagem: {props.path}
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                </label>
                <label>
                    Detalhes: {props.Detalhes}
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Enviar" />
            </form>
          </div>
            <div>Pegar Autor pelo Id={props.autor_id}</div>
          </>
        ))*/}
    </div>
  );
}

export default Detalhes;
