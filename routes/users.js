var express = require('express');
var router = express.Router();
const validator =  require('fastest-validator');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const { User } = require('../models');

const v = new validator();

//Registering New User Account 
router.post('/register', async (req,res) =>{
    const {username, email, password} = req.body;
    
    const schema = {
        username: 'string',
        email: 'string',
        password: 'string',
    }
    const validate = v.validate(req.body, schema);

    if(validate.length){
        return res.status(400).json(validate)
    };

    let emailOnDb = await User.findOne({   
        where: {
            email: email, 
        }
    });

    if(emailOnDb){
        return res.status(400).json({
            message : "your email has been used",
        });
    }

    const hashedpassword = await bcrypt.hash(password, 8);

    const user = await User.create({
        username: username, 
        email: email, 
        password: hashedpassword
    });

    res.status(201).json(user);
});

// router.get('/', verifyUser, (req, res) => {
//     const oldCookieToken = req.cookies.token;


// })
//Login User Account
router.post('/login', async (req,res) => {
    const {email, password} = req.body;
    
    const schema = {
        email: 'string',
        password: 'string',
    }
    const validate = v.validate(req.body, schema);

    if(validate.length){
        return res.status(400).json(validate)
    };

    try{
        const emailOnDb = await User.findOne({   
            where: {
                email
            }
        });
    
        if(!emailOnDb){
            return res.status(400).json({
                message : "Your email is not registered",
            });
        }
    
        const isPasswordValid = await bcrypt.compare(password, emailOnDb.password);
    
        if(!isPasswordValid){
            return res.status(400).json({
                message : "Your password is not registered",
            });
        }
        
        if(emailOnDb && isPasswordValid) {
            jwt.sign({email, password}, "jwt-token-key", {expiresIn: '30s'}, (err, token) => {
            if(err){
                console.log(err);
                res.sendStatus(304);
                return
            }
            const Token = token;    
            res.cookie('token', Token);
            res.json({
                message: "token successfully registered",
                token : Token
            })
        }); 
        }
    }catch {
        return res.status(400).json ({
            message : "Bad request"
        });
    }
});

async function verifyUser(req, res, next){
    const {token} = req.headers;
    const cookieToken = req.cookies.token;

    if(token !== cookieToken){
        return res.json({message: "You're not authenticated"})
    }else{
        jwt.verify(token, "jwt-token-key", (err, data) => {
            if(err){
                console.log(err.message);
                res.json(err);
                return
            }
            req.body = data;

            next();
        });
    }
}

router.get('/verifytoken', verifyUser, (req, res)=> {
    res.json({
        message: "You're authenticated",
        data: req.body 
    });
});

//Logout Account
router.post('/deletetoken', verifyUser, (req, res) => {
    res.clearCookie('token'); // Menghapus cookie dengan nama 'token'
    res.status(200).json({
        message: "Token has been deleted",
        token: null 
    });

})

//Hapus Account
router.delete('/deleteaccount/:email', verifyUser, async (req, res) => {
    try {
      const { email }= req.params;
      const user = await User.findOne({
        where: {
          email,
        }
      });

      res.clearCookie('token');
      await user.destroy();
      
      res.status(200).json({
        message: `Akun dengan email: ${email} telah dihapus`
      });
    } catch (error) {
      res.status(500).json({
        message: "Terjadi kesalahan saat menghapus akun",
        error: error.message
      });
    }
});

module.exports = router;