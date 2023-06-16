var express = require('express');
var router = express.Router();
const validator =  require('fastest-validator');

const { Project } = require('../models');

const v = new validator();

router.get('/:email', async (req,res) =>{
    const {email} = req.params;

    let project = await Project.findAll({   
        where: {email: email}
    });
    return res.status(200).json({
        message: 'Get All Project Succesfully',
        list_project: project || {}
    });
});

router.get('/:email/:projectId', async (req,res) =>{
    const {email,projectId} = req.params;

    let project = await Project.findOne({   
        where: {
            email: email, 
            project_id: projectId
        }
    });

    if(!project){
        return res.status(404).json({
            message : "Project Not Found"
        });
    };
    return res.status(200).json(project);
});

router.post('/create', async (req,res) =>{

    const schema = {
        email: 'string',
        project_name: 'string',
        description: 'string',
        date: 'string|optional',
        note: 'string',
    }
    const validate = v.validate(req.body, schema);

    if(validate.length){
        return res.status(400).json(validate)
    };

    const project = await Project.create(req.body);

    res.status(201).json (project);
});

router.put('/:email/:projectId', async (req,res) => {
    const {email, projectId} = req.params;

    let project = await Project.findOne({   
        where: {
            email: email, 
            project_id: projectId
        }
    });

    if(!project){
        return res.status(404).json({
            message : "Project Not Found"
        });
    }
    const schema = {
        email: 'string|optional',
        project_name: 'string|optional',
        description: 'string|optional',
        date: 'string|optional',
        note: 'string|optional',
    }
    const validate = v.validate(req.body, schema);

    if(validate.length){
        return res.status(400).json(validate)
    };
    
    project = await project.update(req.body);
    res.status(201).json(project);
})

router.delete('/:email/:projectId', async (req,res) => {
    const {email, projectId} = req.params;

    let project = await Project.findOne({   
        where: {
            email: email, 
            project_id: projectId
        }
    });

    if(!project){
        return res.status(404).json({
            message : "project Not Found"
        });
    }

    await project.destroy();

    res.status(200).json({
        message: "project has deleted"
    });
});
module.exports = router;