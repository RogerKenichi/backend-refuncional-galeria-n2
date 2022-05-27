import {
    BrowserRouter as Router,
    Routes,
    Route
} from 'react-router-dom'

import React  from 'react';

import Home from './pages/Home'
import Imagem from './pages/Imagem';
import Autor from './components/Autor/Autor';
import DetalhesImagem from './components/HomeComp/Detalhes';
import DetalhesAutor from './components/Autor/Detalhes';
import CadastroAutor from './components/Autor/Cadastro';
import EditarAutor from './components/Autor/Editar';
import CadastrarTag from './components/Tag/CadastrarTag';
import ListaImagensFiltro from './components/ListaImagensFiltro/ListaImagensFiltro';
import ListaTag from './components/Tag/ListaTag';
import ListaTodasImagensTag from './components/Tag/ListaTodasUmaTag';
import ListaTodasImagensAutor from './components/ListaImagensAutor';
import EditarImagem from './components/HomeComp/Editar';
import Erro from './components/Erro/Erro';

export default function MainRoutes() {
    return(
        <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/Imagem' element={<Imagem/>}/>
            <Route path='/Autor' element={<Autor/>}/>
            <Route path='/Tag/' element={<ListaTag/>} />
            <Route path='/Detalhes/:id' element={<DetalhesImagem/>} />
            <Route path='/DetalhesAutor/:id' element={<DetalhesAutor/>}/>
            <Route path='/CadastrarAutor' element={<CadastroAutor/>}/>
            <Route path='/CadastrarTag' element={<CadastrarTag/>}/>
            <Route path='/ListaImagens' element={<ListaImagensFiltro/>}/>
            <Route path='/ListaTodasImagensTag/:id' element={<ListaTodasImagensTag/>}/>
            <Route path='/ListaTodasImagensAutor/:id' element={<ListaTodasImagensAutor/>}/>
            <Route path='/EditarAutor/:id' element={<EditarAutor/>}/>
            <Route path='/EditarImagem/:id' element={<EditarImagem/>}/>
            <Route path='*' element={<Erro/>}/>
        </Routes>
    )
}