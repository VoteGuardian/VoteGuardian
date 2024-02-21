import VoteManageHeader from "./VoteHeader/VoteManageHeader"
import VoteList from "./VoteContent/VoteList/VoteList"
import { useEffect } from "react";
import { useRecoilState, useSetRecoilState } from 'recoil';
import { voteListState, votePage, voteState, voteTotalPage, voteType } from "@/recoil/atoms/voteAtoms";
import { getCreateVoteList, getParticipateVoteList } from "@/app/api/vote/vote";
import { useSearchParams } from "next/navigation";

export default function VoteManage() {
    //투표 목록(생성한 또는 참여 가능한)
    const setVoteList = useSetRecoilState(voteListState);
    //생성한 투표 목록(또는 참여 가능한 투표 목록)이 가지는 전체 페이지 수
    const setVoteTotalPage = useSetRecoilState(voteTotalPage);
    //투표 상태와 현재 페이지 번호
    const [state, setState] = useRecoilState(voteState);
    const [page, setPage] = useRecoilState(votePage);
    //이메일은 아직 더미
    const email = 'ko123@g.com';
    //어떤 투표 목록을 불러올지에 대해 정하는 변수
    const params = useSearchParams(); //URL query 가져오기
    //query에 type으로 지정된 값을 가져와서 저장
    const type = params.get('type');
    const setVoteType = useSetRecoilState(voteType);

    //회원이 생성한 투표 목록을 불러오기
    //투표 상태(전체, 예정, 진행, 종료)와 페이지가 바뀔 경우 다시 불러오기
    useEffect(() => {
        //투표 관리하기(내가 생성한 투표)
        if(type === 'create') {
            async function fetchCreateVoteList() {
                const voteInfo = await getCreateVoteList(state, page-1, email);
                setVoteList(voteInfo.voteList);
                setVoteTotalPage(voteInfo.totalPageCnt);
            }
            fetchCreateVoteList();
        }
        //투표 참여하기(내가 참여할 수 있는 투표)
        else {
            async function fetchParticipateVoteList() {
                const voteInfo = await getParticipateVoteList(state, page-1, email);
                setVoteList(voteInfo.voteList);
                setVoteTotalPage(voteInfo.totalPageCnt);
            }
            fetchParticipateVoteList();
        }
    }, [state, page])
    //type(생성한 투표냐 참여할 투표냐)가 바뀌면 state와 page는 기초 값으로 다시 변경
    useEffect(() => {
        setVoteType(type);
        //생성한 투표 목록의 기본은 전체, 참여 가능한 투표 목록의 기본은 예정
        if(type === 'create') setState(0);
        else setState(1);
        setPage(1);
    }, [type])
    return(
        <>
            <VoteManageHeader/>
            <VoteList/>
        </>
    )
}