const { atom } = require("recoil");

export const createVoteListState = atom({
    //key : 전역적인 고유 키, default : 초기 값
    key : 'createVoteListState',
    default: []
})

export const pageNumState = atom({
    key: 'pageNumState',
    default: 1
})