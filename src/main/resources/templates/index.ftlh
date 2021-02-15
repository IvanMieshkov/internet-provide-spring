<head>
    <meta charset="utf-8">
    <#import "/spring.ftl" as spring/>

    <#include "fragments/bootstrap.ftl"/>

</head>
<body>
<#include "fragments/nav-bar.ftl" />


<br>
<br>
<br>
<div class="container">

    <p style="line-height: 1; text-align: left;"><strong><span
                    style="font-family: Verdana, Geneva, sans-serif; font-size: 14px;">Інтернет провайдер&nbsp;</span></strong>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Адміністратор реєструє Абонента в системі.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Абоненту надано перелік Послуг (Телефон, Інтернет, Кабельне ТБ, IP-TV тощо) і список Тарифів до кожної послуги.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Реалізувати можливість скачування тарифних планів в одному з форматів (наприклад, txt, pdf, docx).</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Для переліку тарифів реалізувати можливість сортування за:</span></span>
    </p>
    <ul style="list-style-type: circle;">
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;"> - алфавітом/в зворотному порядку (a-z, z-a);</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- за ціною.</span></span>
        </li>
    </ul>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Абонент може обрати одну або кілька послуг за певним тарифом.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">У абонента є Рахунок, який він може поповнити.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Кошти з рахунку знімаються системою в залежності від обраних абонентом тарифних планів.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Якщо суми на рахунку не достатньо, система блокує користувача.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Користувач автоматично розблокується по внесенню необхідної суми на рахунок.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Адміністратор системи володіє правами:</span></span>
    </p>
    <ul style="list-style-type: circle;">
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- додавання, видалення або редагування тарифного плану;&nbsp;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">- реєстрації, блокування та розблокування користувача.&nbsp;</span></span>
        </li>
    </ul>
</div>
<br>
<br>
<#include "fragments/footer.ftlh"/>
</body>
