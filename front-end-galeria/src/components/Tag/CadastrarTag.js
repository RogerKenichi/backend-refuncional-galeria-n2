import React from 'react'

export default function CadastrarTag() {
  return (
      <>
        <div>Cadastro</div>
            <form method="post" action="http://localhost:8080/api/tag/cadastrar" enctype="multipart/form-data">
                <input type="text" name="tag" />
                <input type="submit" value="Cadastrar" />
            </form>
        </>
  )
}
