let dataA = {
    name: '流程模板A',
    nodeList: [
        {
            id: '1234',
            name: '流程A-开始节点',
            type: 'task',
            left: '26px',
            top: '161px',
            ico: 'el-icon-user-solid',
            state: 'no',
        },
        {
            id: '2345',
            name: '流程A-中间节点',
            type: 'task',
            left: '340px',
            top: '161px',
            ico: 'el-icon-goods',
            state: 'no',
        },
        {
            id: '3456',
            name: '流程A-结束节点',
            type: 'task',
            left: '670px',
            top: '161px',
            ico: 'el-icon-present',
            state: 'no',
        }
    ],
    lineList: [{
        from: '1234',
        to: '2345',
    }, {
        from: '2345',
        to: '3456'
    }]
}

import { nanoid } from "nanoid";

export function getDataA() {
    const newDataA = dataA.nodeList.forEach((node) => {
        const newId = nanoid();
        const oriId = node.id;
        node.id = newId;
        // Update lineList from/to fields
        dataA.lineList.forEach((line) => {
            if (line.from === oriId) {
                line.from = newId;
            }
            if (line.to === oriId) {
                line.to = newId;
            }
        });
    });
    return dataA
}
