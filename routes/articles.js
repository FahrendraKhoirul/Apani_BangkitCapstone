var express = require('express');
var router = express.Router();
const validator =  require('fastest-validator');

const { Article } = require('../models');

const v = new validator();

router.get('/', async (req,res) =>{
    const article = await Article.findAll();
    return res.status(200).json({
        message: 'Get All Article Succesfully',
        article_list: article
    });
})

router.get('/:article_id', async (req,res) =>{
    const {article_id} = req.params;
    const article = await Article.findByPk(article_id);
    return res.status(200).json(article || {});
})

router.post('/', async (req,res) =>{
    const schema = {
        title: 'string',
        author: 'string',
        date: 'string|optional',
        article: 'string|text',
        source: 'string',
        img_url: 'string',
    }
    const validate = v.validate(req.body, schema);

    if(validate.length){
        return res.status(400).json(validate)
    }
    
    const article = await Article.create(req.body);

    res.status(201).json(article);
})
module.exports = router;