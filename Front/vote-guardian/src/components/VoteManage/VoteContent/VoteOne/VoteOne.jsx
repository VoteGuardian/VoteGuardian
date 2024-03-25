import './VoteOne.scss';
import { useRecoilValue } from 'recoil';
import { voteListState, voteState, voteType } from "@/recoil/atoms/voteAtoms";
import VoteState from './VoteState/VoteState';
import VoteDate from './VoteDate/VoteDate';
import VoteCreateDate from './VoteCreateDate/VoteCreateDate';

export default function VoteOne() {
    //불러온 (생성한 투표 목록 or 참여 가능한) 투표 목록
    const voteList = useRecoilValue(voteListState);
    //생성한 투표 목록인지 참여 가능한 투표 목록인지 구분할 변수
    const type = useRecoilValue(voteType);
    //투표의 상태(전체, 예정, 진행, 종료)
    const state = useRecoilValue(voteState);

    return(
        <>
        {voteList !== null ? voteList.map((vote) =>
            <div key={vote.id} className='vote-info'>
                {type === 'create' ?
                <VoteState state={vote.state}/>
                : <VoteState state={state}/>
                }
                <div className='vote-content'>
                    <div className='vote-title'>
                        <p>{vote.title}</p>
                    </div>
                    <VoteDate startAt={vote.startAt.substr(0, 16)} finishAt={vote.finishAt.substr(0, 16)}/>
                    {type === 'create' &&
                    <VoteCreateDate createdAt={vote.createdAt}/>
                    }  
                </div>
            </div>
        ) : (state == 0 ? 
            <div>
                <h1>투표가 없습니다</h1>
            </div>
        : (state == 1 ?
            <div>
                <h1>예정중인 투표가 없습니다</h1>
            </div>
        : (state == 2 ?
            <div>
                <h1>진행중인 투표가 없습니다</h1>
            </div>
        : <div>
            <h1>종료된 투표가 없습니다</h1>
        </div>)))}
        </>
    )
}