package com.lojavirtual.controller;

import com.lojavirtual.dto.ConsultaCnpjPjDto;
import com.lojavirtual.dto.PessoaFisicaDTO;
import com.lojavirtual.dto.PessoaJuridicaDTO;
import com.lojavirtual.dto.PessoaResponseDto;
import com.lojavirtual.service.ConsultaCnpjService;
import com.lojavirtual.service.PessoaUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PessoaController {
    private final PessoaUserService pessoaUserService;
    private final ConsultaCnpjService consultaCnpjService;

    @ResponseBody
    @GetMapping(value = "/consultarCnpj/{cnpj}")
    public ResponseEntity<ConsultaCnpjPjDto> consultarCnpj(@PathVariable("cnpj") String cnpj) {
        var resultadoConsulta = consultaCnpjService.consultaCnpjReceitaFederal(cnpj);
        return new ResponseEntity<>(resultadoConsulta, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarPessoaFisicaPorNome/{nome}")
    public ResponseEntity<List<PessoaFisicaDTO>> consultarPessoaFisicaPorNome(@PathVariable("nome") String nome) {
        var pessoaFisicaList = pessoaUserService.buscarPessoaFisicaPorNome(nome);
        return new ResponseEntity<>(pessoaFisicaList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarPessoaFisicaPorCpf/{cpf}")
    public ResponseEntity<PessoaFisicaDTO> consultarPessoaFisicaPorCpf(@PathVariable("cpf") String cpf) {
        var pessoaFisica = pessoaUserService.buscarPessoaFisicaPorCpf(cpf);
        return new ResponseEntity<>(pessoaFisica, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/cadastrarPessoaFisica")
    public ResponseEntity<PessoaResponseDto> salvarPessoaFisica(@RequestBody @Valid PessoaFisicaDTO pessoaFisicaDTO, HttpServletResponse response) {
        var pessoaFisicaRegistrada = pessoaUserService.guardarPessoaFisica(pessoaFisicaDTO);

        var jwtTokenCookie = new Cookie("token", pessoaFisicaRegistrada.getToken());

        jwtTokenCookie.setMaxAge(2 * 24 * 60 * 60);
        jwtTokenCookie.setHttpOnly(true);
        jwtTokenCookie.setSecure(false);
        jwtTokenCookie.setPath("/");

        response.addCookie(jwtTokenCookie);

        return new ResponseEntity<>(pessoaFisicaRegistrada, HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping(value = "/atualizarPessoaFisica")
    public ResponseEntity<PessoaResponseDto> atualizarPessoaFisica(@RequestBody @Valid PessoaFisicaDTO pessoaFisicaDTO) {
        var pessoaFisicaAtualizada = pessoaUserService.guardarPessoaFisica(pessoaFisicaDTO);
        return new ResponseEntity<>(pessoaFisicaAtualizada, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarPessoaJuridicaPorNome/{nome}")
    public ResponseEntity<List<PessoaJuridicaDTO>> consultarPessoaJuridicaPorNome(@PathVariable("nome") String nome) {
        var pessoaJuridicaList = pessoaUserService.buscarPessoaJuridicaPorNome(nome);
        return new ResponseEntity<>(pessoaJuridicaList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarPessoaJuridicaPorCnpj/{cnpj}")
    public ResponseEntity<PessoaJuridicaDTO> consultarPessoaJuridicaPorCnpj(@PathVariable("cnpj") String cnpj) {
        var pessoaJuridica = pessoaUserService.buscarPessoaJuridicaPorCnpj(cnpj);
        return new ResponseEntity<>(pessoaJuridica, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/cadastrarPessoaJuridica")
    public ResponseEntity<PessoaResponseDto> salvarPessoaJuridica(@RequestBody @Valid PessoaJuridicaDTO pessoaJuridicaDTO, HttpServletResponse response) {
        var pessoaJuridicaRegistrada = pessoaUserService.guardarPessoaJuridica(pessoaJuridicaDTO);

        var jwtTokenCookie = new Cookie("token", pessoaJuridicaRegistrada.getToken());

        jwtTokenCookie.setMaxAge(2 * 24 * 60 * 60);
        jwtTokenCookie.setHttpOnly(true);
        jwtTokenCookie.setSecure(false);
        jwtTokenCookie.setPath("/");

        response.addCookie(jwtTokenCookie);

        return new ResponseEntity<>(pessoaJuridicaRegistrada, HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping(value = "/atualizarPessoaJuridica")
    public ResponseEntity<PessoaResponseDto> atualizarPessoaJuridica(@RequestBody @Valid PessoaJuridicaDTO pessoaJuridicaDTO) {
        var pessoaJuridicaAtualizada = pessoaUserService.guardarPessoaJuridica(pessoaJuridicaDTO);
        return new ResponseEntity<>(pessoaJuridicaAtualizada, HttpStatus.OK);
    }
}
