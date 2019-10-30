INSERT INTO public.templates(template_name, template_html)
VALUES('helloworld',
       '<html>
           <head>
               <title>Hello World</title>
           </head>
           <body>
               <p>%%GREETING%%</p>
           </body>
       </html>'),
      ('cvtemplate',
      '<html>
          <head>
              <title>CV</title>
          </head>
          <body>
          <table width="1200" bgcolor="#efefef">
              <tr>
                  <td>
                      <p style="margin-left: 0.1in">
                          <font face="Segoe UI, serif" size="7">
                              <b>%%NAME%%</b>
                          </font>
                      </p>
                  </td>
              </tr>
              <tr>
                  <td>
                      <p style="margin-left: 0.1in">
                          <font face="Segoe UI, serif" size="4">
                              <b>%%POSITION%%</b>
                          </font>
                      </p>
                  </td>
              </tr>
              <tr>
                  <td>
                      <p style="margin-left: 0.1in">
                          <font face="Segoe UI, serif" size="2">
                              <b>%%STARTDATE%%</b>
                          </font>
                      </p>
                  </td>
              </tr>
          </table>
          </body>
      </html>');