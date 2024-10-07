let dataB = {
    name: '系统默认模板',
    nodeList: [
        {
            id: 'nodeA',
            name: '流程B-节点A',
            type: 'no',
            // left: '18px',
            // top: '223px',
            left: '0px',
            top: '0px',
            ico: 'el-icon-user-solid',
            state: 'no',
            // viewOnly: true
        },
        {
            id: 'nodeB',
            type: 'no',
            name: '流程B-节点B',
            // left: '351px',
            // top: '96px',
            left: '1px',
            top: '281px',
            ico: 'el-icon-goods',
            state: 'no'
        },
        {
            id: 'nodeC',
            name: '流程B-节点C',
            type: 'no',
            // left: '354px',
            // top: '351px',
            left: '394px',
            top: '281px',
            ico: 'el-icon-present',
            state: 'no'
        }, {
            id: 'nodeD',
            name: '流程B-节点D',
            type: 'no',
            // left: '700px',
            // top: '215px',
            left: '452px',
            top: '538px',
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
