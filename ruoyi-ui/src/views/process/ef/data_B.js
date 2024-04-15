let dataB = {
    name: '流程模板B',
    nodeList: [
        {
            id: 'nodeA',
            name: '流程B-节点A',
            type: 'task',
            left: '18px',
            top: '223px',
            ico: 'el-icon-user-solid',
            state: 'no',
            // viewOnly: true
        },
        {
            id: 'nodeB',
            type: 'task',
            name: '流程B-节点B',
            left: '351px',
            top: '96px',
            ico: 'el-icon-goods',
            state: 'no'
        },
        {
            id: 'nodeC',
            name: '流程B-节点C',
            type: 'task',
            left: '354px',
            top: '351px',
            ico: 'el-icon-present',
            state: 'no'
        }, {
            id: 'nodeD',
            name: '流程B-节点D',
            type: 'task',
            left: '700px',
            top: '215px',
            ico: 'el-icon-present',
            state: 'no'
        }
    ],
    lineList: [{
        from: 'nodeA',
        to: 'nodeB',
        label: '条件A'
    }, {
        from: 'nodeA',
        to: 'nodeC',
        label: '条件B'
    }, {
        from: 'nodeB',
        to: 'nodeD'
    }, {
        from: 'nodeC',
        to: 'nodeD'
    }
    ]
}
import { nanoid } from "nanoid";
export function getDataB () {
    const newDataB = dataB.nodeList.forEach((node) => {
        const newId = nanoid();
        const oriId = node.id;
        node.id = newId;
        // Update lineList from/to fields
        dataB.lineList.forEach((line) => {
            if (line.from === oriId) {
                line.from = newId;
            }
            if (line.to === oriId) {
                line.to = newId;
            }
        });
    });
    return dataB
}