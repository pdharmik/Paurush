﻿/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'Hoje';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Dom',
'Seg',
'Ter',
'Qua',
'Qui',
'Sex',
'Sáb'
);
text['dayNames'] = new Array(
'Domingo',
'Segunda',
'Terça',
'Quarta',
'Quinta',
'Sexta',
'Sábado'
);

text['monthNamesShort'] = new Array(
'Jan',
'Fev',
'Mar',
'Abr',
'Mai',
'Jun',
'Jul',
'Ago',
'Set',
'Out',
'Nov',
'Dez'
);

text['monthNames'] = new Array(
'Janeiro',
'Fevereiro',
'Maro',
'Abril',
'Maio',
'Junho',
'Julho',
'Agosts',
'Setembro',
'Outobro',
'Novembro',
'Dezembro'
);

//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%d/%m/%Y',
text['dateFormat'] = '%d/%m/%Y',
text['footerDefaultText'] = 'Seleccionar data',

text['clear'] = 'Limpar Data',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'Fechar',
text['startDay'] = '0',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";

//select and cancel
text['select'] = "Seleccionar";
text['cancel'] = "Cancelar";

RichCalendar.rc_lang_data['pt_BR'] = text;
