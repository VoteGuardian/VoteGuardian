import { recoilPersist } from "recoil-persist";
const { atom } = require("recoil");
//key : 전역적인 고유 키, default : 초기 값
const sessionStorage = typeof window !== 'undefined' ? window.sessionStorage : undefined
const { persistAtom } = recoilPersist({
    key: 'persist',
    storage: sessionStorage
})


//생성한 투표 목록인지 참여 가능한 투표 목록인지 판단
export const voteType = atom({
    key: 'voteType',
    default: 'create'
})
//생성한 투표나 참여 가능한 투표 목록
export const voteListState = atom({
    key: 'voteListState',
    default: []
})
//투표 상태(전체, 예정, 진행, 종료)
export const voteState = atom({
    key: 'voteState',
    default: 0
})
//불러온 투표 목록이 가지는 전체 페이지 수
export const voteTotalPage = atom({
    key: 'voteTotalPage',
    default: 0
})
//현재 페이지 번호
export const votePage = atom({
    key: 'votePage',
    default: 1
})
//투표를 생성할 때 넣을 정보
export const voteInfo = atom({
    key: 'voteInfo',
    default: {},
    effects_UNSTABLE: [persistAtom]
})
//후보자 목록
export const candidateListState = atom({
    key: 'candidateListState',
    default: [],
    effects_UNSTABLE: [persistAtom]
})

// const candidate = {
//     num: int,
//     name : string,
//     tag,
//     content : string,
//     picture : boolean
// }

// const candidateList = candidate[];

// const candidateListState = atom<candidate>({
//     key: 'todoListState',
//     default: [],
//   })

// const candidateList = atomFamily<Candidate, CandidateNum>({
//     key: "candidateList",
//     default: (num) => ({
//         num,
//         picture: false,
//         name :'',
//         content: '',
//         tag: []
//     })
// })

//아래 두 개는 voteListState로 대체된 상태
//회원이 생성한 투표 목록
// export const createVoteListState = atom({
//     key : 'createVoteListState',
//     default: []
// })

//참여할 수 있는 투표 목록
// export const participateVoteListState = atom({
//     key: 'participateVoteListState',
//     default: []
// })