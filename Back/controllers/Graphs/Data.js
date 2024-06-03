const { Pesos } = require('../../models/User');

exports.obtener_pesos = async (req, res) => {
    try {
        const { usuario_id } = req.body;
        const pesos = await Pesos.findAll({
            where: {
                usuario_id
            },
            order: [
                ['createdAt', 'ASC']
            ],
            attributes: ['peso']
        });

        const pesosArray = pesos.map(p => parseFloat(p.peso));

        res.status(200).json({ pesos: pesosArray });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
}
