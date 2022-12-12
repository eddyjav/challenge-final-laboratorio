package org.krugerjy.laboratorio.msvusuarios.services;

import org.krugerjy.laboratorio.msvusuarios.clients.AnalisisClientRest;
import org.krugerjy.laboratorio.msvusuarios.clients.ResultadoClientRest;
import org.krugerjy.laboratorio.msvusuarios.models.Analisis;
import org.krugerjy.laboratorio.msvusuarios.models.Resultado;
import org.krugerjy.laboratorio.msvusuarios.models.entity.Usuario;
import org.krugerjy.laboratorio.msvusuarios.models.entity.UsuarioAnalisis;
import org.krugerjy.laboratorio.msvusuarios.models.entity.UsuarioResultado;
import org.krugerjy.laboratorio.msvusuarios.repositories.UsuarioRepository;
import org.krugerjy.laboratorio.msvusuarios.request.CreateFichaAnalisis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements  UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AnalisisClientRest clientRest;

    @Autowired
    private ResultadoClientRest resultadoClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> byIdWithAnalisis(Long id) {
        Optional<Usuario> o = repository.findById(id);
        if(o.isPresent()){
            Usuario usuario = o.get();
            if(!usuario.getUsuarioAnalisisP().isEmpty()){
                List<Long> ids = usuario.getUsuarioAnalisisP().stream().map(ua -> ua.getAnalisisId())
                        .collect(Collectors.toList());

                List<Long> idsr = usuario.getUsuarioResultados().stream().map(ua -> ua.getResultadoId())
                        .collect(Collectors.toList());

                List<Analisis> analisisP = clientRest.obtenerAnalisisPorUsuario(ids);
                List<Resultado> resultados = resultadoClientRest.obtenerResultadosPorUsuarios(idsr);

                usuario.setAnalisisP(analisisP);
                usuario.setResultados(resultados);
            }
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> byIdWithResultados(Long id) {
        Optional<Usuario> o = repository.findById(id);

        if(o.isPresent()){
            Usuario usuario = o.get();
            if(!usuario.getUsuarioResultados().isEmpty()){
                List<Long> ids = usuario.getUsuarioResultados().stream().map(ur -> ur.getResultadoId())
                        .collect(Collectors.toList());

                List<Resultado> resultadosP = resultadoClientRest.obtenerResultadosPorUsuarios(ids);
                usuario.setResultados(resultadosP);
            }
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteUsuarioAnalisisById(Long id) {
        repository.deleteUsuarioAnalisisById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }



    @Override
    @Transactional
    public Optional<Analisis> asignarAnalisis(Analisis analisis, Long analisisId) {
        Optional<Usuario> o = repository.findById(analisisId);
        if(o.isPresent()){
            Analisis analisisMsv = clientRest.detail(analisis.getId());

            Usuario usuario = o.get();
            UsuarioAnalisis usuarioAnalisis = new UsuarioAnalisis();
            usuarioAnalisis.setAnalisisId(analisisMsv.getId());

            usuario.addUsuarioAnalisis(usuarioAnalisis);
            repository.save(usuario);
            return Optional.of(analisisMsv);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Analisis> crearAnalisis(Analisis analisis, Long analisisId) {
        Optional<Usuario> o = repository.findById(analisisId);
        if(o.isPresent()){
            Analisis analisisNuevoMsv = clientRest.create(analisis);

            Usuario usuario = o.get();
            UsuarioAnalisis usuarioAnalisis = new UsuarioAnalisis();
            usuarioAnalisis.setAnalisisId(analisisNuevoMsv.getId());

            usuario.addUsuarioAnalisis(usuarioAnalisis);
            repository.save(usuario);

            //FICHA-RESULTADOS
            CreateFichaAnalisis cf = new CreateFichaAnalisis();
            Resultado resultadoNuevoMsv = resultadoClientRest.create(cf.Agregarficha(analisisNuevoMsv, usuario));
            UsuarioResultado usuarioResultado = new UsuarioResultado();
            usuarioResultado.setResultadoId(resultadoNuevoMsv.getId());
            usuario.addUsuarioResultado(usuarioResultado);
            repository.save(usuario);

            return Optional.of(analisisNuevoMsv);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Analisis> eliminarAnalisis(Analisis analisis, Long analisisId) {
        Optional<Usuario> o = repository.findById(analisisId);
        if(o.isPresent()){
            Analisis analisisMsv = clientRest.detail(analisis.getId());

            Usuario usuario = o.get();
            UsuarioAnalisis usuarioAnalisis = new UsuarioAnalisis();
            usuarioAnalisis.setAnalisisId(analisisMsv.getId());

            usuario.removeUsuarioAnalisis(usuarioAnalisis);
            repository.save(usuario);
            return Optional.of(analisisMsv);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Resultado> asignarResultado(Resultado resultado, Long resultadoId) {
        Optional<Usuario> o = repository.findById(resultadoId);
        if(o.isPresent()){
            Resultado resultadoMsv = resultadoClientRest.detail(resultado.getId());

            Usuario usuario = o.get();
            UsuarioResultado usuarioResultado = new UsuarioResultado();
            usuarioResultado.setResultadoId(resultadoMsv.getId());

            usuario.addUsuarioResultado(usuarioResultado);
            repository.save(usuario);
            return  Optional.of(resultadoMsv);
        }
        return Optional.empty();
    }


}
