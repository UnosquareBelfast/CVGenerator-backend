INSERT INTO public.templates(template_name, template_html)
VALUES('helloworld',
       '<html>
           <head>
               <title>Hello World</title>
           </head>
           <body>
               <p>~~greeting~~</p>
           </body>
       </html>'),
      ('cvtemplate',
      '<html>

<head>
    <style type="text/css">
        p {
            font-family: "Segoe UI", serif;
            margin-left: 0.2in;
        }
    </style>
    <title>CV</title>
</head>

<body>
    <table width="1000" bgcolor="#efefef">

        <tr ~~TOP_LEVEL_START~~>
            <td>
                <p style="font-size: 36">
                        <b>~~firstName~~ ~~lastName~~</b>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 10">
                    <b>~~role~~</b>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 8">
                    <b>~~email~~</b>
                </p>
            </td>
        </tr ~~TOP_LEVEL_END~~>

    </table>
    <p style="font-size: 14">
        <b>PROFILE</b>
    </p>
    <p style="font-size: 9; color: #808080">
        PLACEHOLDER
    </p>

    <br/>

    <p style="font-size: 14">
        <b>TECHNICAL SKILLS</b>
    </p>
    <table width="1000">
        <tr bgcolor="#efefef">
            <td>
                <p style="font-size: 10">
                    <b>TECHNOLOGIES:</b>
                </p>
            </td>

            <td>
                <p style="font-size: 10">
                    <b>METHODOLOGIES:</b>
                </p>
            </td>

            <td>
                <p style="font-size: 10">
                    <b>CERTIFICATIONS:</b>
                </p>
            </td>
        </tr>

        <tr>
            <td>
                <ul>
                    <li>
                        <p style="font-size: 9; color: #808080">
                            PLACEHOLDER
                        </p>
                    </li>
                </ul>
            </td>

            <td>
                <ul>
                    <li>
                        <p style="font-size: 9; color: #808080">
                            PLACEHOLDER
                        </p>
                    </li>
                </ul>
            </td>

            <td>
                <ul>
                  ~~QUAL_LEVEL_START~~
                    <li>
                        <p style="font-size: 9; color: #808080">
                            ~~institution~~ - ~~name~~
                        </p>
                    </li>
                  ~~QUAL_LEVEL_END~~
                </ul>
            </td>
        </tr>
    </table>

    <br/>

    <p style="font-size: 14">
        <b>WORK EXPERIENCE</b>
    </p>

    <br/>

    <table cellpadding="20" cellspacing="0" width="100%" valign="TOP" style="border-top: none; border-bottom: none; border-left: 2.25pt dotted #bfbfbf; border-right: none; padding-top: 0in; padding-bottom: 0in; padding-left: 0.1in; padding-right: 0in">
        ~~WORK_LEVEL_START~~
            <tr>
                <td>
                    <p style="font-size: 12">
                        <b style="color: #0070c0">~~company~~</b>
                        <b >Project (~~startDate~~ - ~~endDate~~)</b>
                    </p>
                    <p style="font-size: 9">
                        <i><b>~~position~~</b></i>
                    </p>

                    <br/>

                    <p style="font-size: 9; color: #808080">
                        PLACEHOLDER
                    </p>

                    <br/>

                    <ul style="font-size: 9; color: #808080">
                        <li>
                            <p>
                                PLACEHOLDER
                            </p>
                        </li>
                        <li>
                            <p>
                                PLACEHOLDER
                            </p>
                        </li>
                    </ul>

                </td>
            </tr>
        ~~WORK_LEVEL_END~~
    </table>

</body>

</html>');