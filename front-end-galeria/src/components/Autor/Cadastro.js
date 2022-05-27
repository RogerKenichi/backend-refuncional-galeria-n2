import React from 'react'
import api from '../../services/apiAutor';
import { useNavigate } from 'react-router-dom'

export default function Cadastro() {
  const navigate = useNavigate();

  function changePage()
  {
    navigate('/Autor');
  }

  return (
    <>
      <div>Cadastro</div>
      <form method="post" action="http://localhost:8080/api/autor/cadastrar" enctype="multipart/form-data">
        <labe>Nome do autor: </labe><input type="text" name="nome" />
        <br />
        <labe>Descrição: </labe><input type="text" name="descricao" />
        <input type="submit" value="Cadastrar" />
      </form>
    </>
  )
}
