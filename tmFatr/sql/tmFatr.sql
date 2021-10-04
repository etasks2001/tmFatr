
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
select * from information_schema.table_constraints;




DO $$
declare r record;
begin
	raise info '%','----------------------------------------------------------------------------------';
	for r in (select  constraint_name, table_name from  information_schema.table_constraints where constraint_type = 'FOREIGN KEY') loop
	execute CONCAT('ALTER TABLE "' || r.table_name || '" DROP CONSTRAINT '||r.constraint_name);
	raise info '%','dropping '||r.constraint_name;
	end loop;
	raise info '%','----------------------------------------------------------------------------------';
end;
$$;
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
drop table if exists emit;
drop table if exists serie;
drop table if exists nf;
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
drop function if exists create_id_emit_serie_sequence();
drop function if exists numero_nNF();

/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

create table emit(
	id serial,
	CNPJ varchar(14) not null, 
	xNome varchar(60) not null, 
	xFant varchar(60) null, 
	xLgr varchar(60) not null, 
	nro varchar(60) not null, 
	xCpl varchar(60) null, 
	xBairro varchar(60)  not null, 
	cMun varchar(7)  not null, 
	xMun varchar(60) not null, 
	UF varchar(2)  not null, 
	CEP varchar(8) not null, 
	cPais varchar(4) not null, 
	xPais varchar(60) not null, 
	fone varchar(14) null,
	IE varchar(14) not null, 
	IEST varchar(14) null, 
	IM varchar(15) not null, 
	CNAE varchar(7) null,
	CRT varchar(1) not null,
	nf_serie_atual int not null,
	ultima_nnf int not null,
	primary key (id),
	unique (CNPJ)
);

/*---------------------------------------------------------------------------------------------------------------------------*/
create table serie(
  id_emit int references emit,
  serie int not null,
  nnf_inicial int not null,
  nnf_final int not null,
  primary key (id_emit, serie)
);





create table nf(
  sk serial primary key,
  id_emit integer references emit,

  cnpj varchar(14) not null,
  serie int not null, --0 a 999
  nNF int not null, --1 a 999.999.999
  
  ind_emit varchar(1) not null, /*0-Emissão própria;
                      1-Terceiros
                      @CAMPO ICMS IPI
                      */
  
  versao varchar(4) not null,
  id varchar(44) not null, -- chave NF-e
  cUF varchar(2) not null, -- CODIGO UF
  cNF varchar(8) not null, --ALEATÓRIO
  natOp varchar(60) not null, 
  mod varchar(2) not null, /*55-NF-e*/
  
  
  dhEmi timestamp not null,
  dhSaiEnt timestamp null,
  tpNF varchar(1) not null, /*0-Entrada
                           1-Saída
			   @CAMPO ICMS IPI - "IND_OPER"
                           */

  idDest varchar(1) not null,/*1-Operação interna;
                            2-Operação interestadual;
                            3-Operação com exterior*/
  cMunFG varchar(7) not null, --Código município
  tpImp varchar(1) not null, /*0-Sem geração de DANFE;
                            1-DANFE normal, Retrato;
                            2-DANFE normal, Paisagem;
                            3-DANFE Simplificado;
                            4-DANFE NFC-e;
                            5-DANFE NFC-e*/
  tpEmis varchar(1) not null, /*1-Emissão normal (não em contingência);
                             2-Contingência FS-IA, com impressão do DANFE em formulário de segurança;
                             3-Contingência SCAN (Sistema de Contingência do Ambiente Nacional);
                             4-Contingência DPEC (Declaração Prévia da Emissão em Contingência);
                             5-Contingência FS-DA, com impressão do DANFE em formulário de segurança;
                             6-Contingência SVC-AN (SEFAZ Virtual de Contingência do AN);
                             7-Contingência SVC-RS (SEFAZ Virtual de Contingência do RS);
                             9-Contingência off-line da NFC-e (as demais opções de contingência são válidas também para a NFC-e).*/


  cDV varchar(1) not null, --DV da Chave de Acesso da NF-e
  tpAmb varchar(1) not null, /*1-Produção
                            2-Homologação*/
  finNFe varchar(1) not null, /*1-NF-e normal;
                             2-NF-e complementar;
                             3-NF-e de ajuste;
                             4-Devolução de mercadoria*/
  indFinal varchar(1) not null, /*0-Normal;
                               1-Consumidor final;*/


  indPres varchar(1) not null, /*0-Não se aplica (por exemplo, Nota Fiscal complementar ou de ajuste);
                              1-Operação presencial;
                              2-Operação não presencial, pela Internet;
                              3-Operação não presencial, Teleatendimento;
                              4-NFC-e em operação com entrega a domicílio;
                              9-Operação não presencial, outros.*/

  indIntermed varchar(1) null, 
  procEmi varchar(1) not null, /*0-Emissão de NF-e com aplicativo do contribuinte;
                              1-Emissão de NF-e avulsa pelo Fisco;
                              2-Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco;
                              3-Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco*/
  verProc varchar(20) not null, 
  dhCont timestamp null, --CONTINGÊNCIA data
  xJust varchar(256) null, --CONTINGÊNCIA justificativa de 15 a 256
  unique  (cnpj, serie, nNF)
);




/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/









select to_char( round(random() * 99999999),'00000000');



CREATE FUNCTION numero_nNF() RETURNS trigger
  LANGUAGE plpgsql
  AS $$
begin
  if NEW.ind_emit = '0' then
	NEW.nNF := nextval('id_emit_'|| NEW.id_emit ||'_serie_' || NEW.serie);
	NEW.cNF := trim(to_char(round(random() * 99999999),'00000000'));
  elseif NEW.ind_emit = '1' then
	NEW.tpNF = '0';
  end if;
  RETURN NEW;
end
$$;

CREATE TRIGGER numero_nNF BEFORE INSERT ON nf FOR EACH ROW EXECUTE PROCEDURE numero_nNF();




CREATE FUNCTION create_id_emit_serie_sequence() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
  execute format('drop sequence if exists id_emit_%s_serie_%s', NEW.id_emit, NEW.serie, NEW.nnf_inicial, NEW.nnf_final );
  execute format('create sequence id_emit_%s_serie_%s minvalue %s  maxvalue %s', NEW.id_emit, NEW.serie, NEW.nnf_inicial, NEW.nnf_final );
  return NEW;
end
$$;

CREATE TRIGGER create_id_emit_serie_sequence AFTER INSERT ON serie FOR EACH ROW EXECUTE PROCEDURE create_id_emit_serie_sequence();


/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/






insert into emit(CNPJ,xNome,xFant,xLgr,nro,xCpl,xBairro,cMun,xMun,UF,CEP,cPais,xPais,fone,IE,IEST,IM,CNAE,CRT,nf_serie_atual,ultima_nnf) values
           ('00000000000000','empresa','','rua sem saida','150','','centro','0000000','são paulo','sp','12345678','1234','brasil','12478','111222333444','','1111111','1234567','2',0,25965);
insert into emit(CNPJ,xNome,xFant,xLgr,nro,xCpl,xBairro,cMun,xMun,UF,CEP,cPais,xPais,fone,IE,IEST,IM,CNAE,CRT,nf_serie_atual,ultima_nnf) values
           ('11111111111111','empresa','','rua sem saida','150','','centro','0000000','são paulo','sp','12345678','1234','brasil','12478','111222333444','','1111111','1234567','2',0,0);



insert into serie(id_emit, serie, nnf_inicial, nnf_final) values (1,0, 1, 999999999);
insert into serie(id_emit, serie, nnf_inicial, nnf_final) values (1,1, 5555, 999999999);
--insert into serie(id_emit, serie, nnf_inicial, nnf_final) values (1,55, 1555, 999999999);




do $$
declare 
   counter integer := 0;
begin
   while counter < 55 loop
	insert into nf (id_emit,cnpj,serie,nnf,ind_emit,versao,id,cuf,cnf,natop,mod,dhemi,dhsaient,tpnf,iddest,cmunfg,tpimp,tpemis,cdv,tpamb,finnfe,indfinal,indpres,indIntermed,procemi,verproc,dhcont,xjust) values 
	(1,'00000000000000',0,2,0,'1.4','11111111112222222222333333333344444444445555','35','17897897','vendas','55','01/01/2001',null,1,1,1234567,1,1,1,2,1,0,0,null,0,'1.1',null,null);	

	insert into nf (id_emit,cnpj,serie,nnf,ind_emit,versao,id,cuf,cnf,natop,mod,dhemi,dhsaient,tpnf,iddest,cmunfg,tpimp,tpemis,cdv,tpamb,finnfe,indfinal,indpres,indIntermed,procemi,verproc,dhcont,xjust) values 
	(1,'00000000000000',1,2,0,'1.4','11111111112222222222333333333344444444445555','35','17897897','vendas','55','01/01/2001',null,1,1,1234567,1,1,1,2,1,0,0,null,0,'1.1',null,null);	

	counter := counter + 1;
   end loop;
end$$;
	insert into nf (id_emit,cnpj,serie,nnf,ind_emit,versao,id,cuf,cnf,natop,mod,dhemi,dhsaient,tpnf,iddest,cmunfg,tpimp,tpemis,cdv,tpamb,finnfe,indfinal,indpres,indIntermed,procemi,verproc,dhcont,xjust) values 
	(1,'11111111111111',0,19999,1,'1.4','11111111112222222222333333333344444444445555','35','17897897','vendas','55','01/01/2001',null,1,1,1234567,1,1,1,2,1,0,0,null,0,'1.1',null,null);	


select * from nf  ;
select * from emit;

select * from serie;

--delete from serie where serie = 55

