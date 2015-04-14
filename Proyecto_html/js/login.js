
function signin()
      {

        form=document.getElementById('signinForm');
        
        var user = document.getElementById('user').value;
        var password = document.getElementById('password').value;

        if (user == "comprador" && password ===user)
        {
            window.location.href ='html/comprador_catalogo_camiseta.html';
            
        }else if(user == "artista" && password ===user)
        {
            window.location.href ='html/artista_inic.html';
            
        }
        else if(user == "admin" && password ===user)
        {
            window.location.href ='html/admin_inic.html';
        }else{
            
            alert ("Usuario o contraseña incorrecta");
            window.location.href ='vabrik.html';
        }
        
        
        
      }